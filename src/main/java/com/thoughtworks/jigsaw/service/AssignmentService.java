package com.thoughtworks.jigsaw.service;

import com.thoughtworks.jigsaw.domain.Assignment;
import com.thoughtworks.jigsaw.domain.Employee;
import com.thoughtworks.jigsaw.domain.Project;

import java.util.Collections;
import java.util.List;

public class AssignmentService {

    public boolean assign(Assignment assignment) {
        Employee employee = assignment.getEmployee();
        Project project = assignment.getProject();

        boolean suitable = employee.isSuitableFor(project);
        if(!suitable) return false;

        List<Assignment> assignments = project.getAssignments();
        if(assignments == null) {
            project.setAssignments(Collections.singletonList(assignment));
        } else {
            assignments.add(assignment);
            project.setAssignments(assignments);
        }

        return true;
    }
}
