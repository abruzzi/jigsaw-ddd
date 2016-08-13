package com.thoughtworks.jigsaw.service;

import com.thoughtworks.jigsaw.domain.Employee;
import com.thoughtworks.jigsaw.domain.Project;
import com.thoughtworks.jigsaw.repository.EmployeeRepository;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StaffingService {
    private EmployeeRepository employeeRepository;

    public StaffingService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    private Stream<Employee> streamify(Iterable<Employee> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    public Iterable<Employee> getAssignableEmployees() {
        Iterable<Employee> all = employeeRepository.findAll();
        return streamify(all)
                .filter((employee -> employee.getCurrentProject() == null))
                .collect(Collectors.toList());
    }

    public Iterable<Employee> suitableEmployeesForProject(Project project) {
        Iterable<Employee> all = employeeRepository.findAll();
        return streamify(all)
                .filter(employee -> employee.getCurrentProject() == null)
                .filter(employee -> employee.isSuitableFor(project))
                .collect(Collectors.toList());
    }

}
