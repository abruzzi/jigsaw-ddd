package com.thoughtworks.jigsaw.service;

import com.thoughtworks.jigsaw.domain.Assignment;
import com.thoughtworks.jigsaw.domain.Employee;
import com.thoughtworks.jigsaw.domain.Project;
import com.thoughtworks.jigsaw.exception.NotProjectReadyException;
import com.thoughtworks.jigsaw.repository.AssignmentRepository;
import com.thoughtworks.jigsaw.repository.EmployeeRepository;
import com.thoughtworks.jigsaw.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class AssignmentService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Transactional
    public Assignment assign(Employee employee, Project project, Date from, Date to) throws NotProjectReadyException {
        if(employee.getId() == 0) {
            employeeRepository.save(employee);
        }

        if(project.getId() == 0) {
            projectRepository.save(project);
        }

        boolean projectReady = employee.isAssignable() && employee.isSuitableFor(project);
        if(!projectReady) {
            throw new NotProjectReadyException("Employee is not ready for this project");
        }

        Assignment assignment = new Assignment(project, employee, from, to);
        return assignmentRepository.save(assignment);
    }
}
