package com.thoughtworks.jigsaw.service;

import com.thoughtworks.jigsaw.domain.Assignment;
import com.thoughtworks.jigsaw.domain.Employee;
import com.thoughtworks.jigsaw.domain.Project;
import com.thoughtworks.jigsaw.repository.AssignmentRepository;
import com.thoughtworks.jigsaw.repository.EmployeeRepository;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StaffingService {
    private EmployeeRepository employeeRepository;
    private AssignmentRepository assignmentRepository;

    public StaffingService(EmployeeRepository employeeRepository, AssignmentRepository assignmentRepository) {
        this.employeeRepository = employeeRepository;
        this.assignmentRepository = assignmentRepository;
    }

    private Stream<Employee> streamify(Iterable<Employee> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    public Iterable<Employee> getAssignableEmployees() {
        Iterable<Employee> all = employeeRepository.findAll();
        return streamify(all)
                .filter((this::isEmployeeCurrentlyAssignable))
                .collect(Collectors.toList());
    }

    private boolean isEmployeeCurrentlyAssignable(Employee employee) {
        return hasRolledOff(employee) || isOnTheBeach();
    }

    private boolean isOnTheBeach() {
        Iterable<Assignment> beach = assignmentRepository.findByProject(new Project("ThoughtWorks Core"));
        return StreamSupport.stream(beach.spliterator(), false).collect(Collectors.toList()).size() > 0;
    }

    private boolean hasRolledOff(Employee employee) {
        Iterable<Assignment> rolledOff = assignmentRepository.findByEmployeeAndEndDateBefore(employee, new Date());
        return StreamSupport.stream(rolledOff.spliterator(), false).collect(Collectors.toList()).size() > 0;
    }

    public Iterable<Employee> suitableEmployeesForProject(Project project) {
        Iterable<Employee> all = employeeRepository.findAll();
        return streamify(all)
                .filter(this::isEmployeeCurrentlyAssignable)
                .filter(employee -> employee.isSuitableFor(project))
                .collect(Collectors.toList());
    }

    public boolean assign(Assignment assignment) {
        Project project = assignment.getProject();
        boolean suitable = assignment.getEmployee().isSuitableFor(project);

        assignment.updateRelations();
        assignmentRepository.save(assignment);

        return suitable;
    }
}
