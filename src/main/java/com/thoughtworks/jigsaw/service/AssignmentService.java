package com.thoughtworks.jigsaw.service;

import com.thoughtworks.jigsaw.domain.Assignment;
import com.thoughtworks.jigsaw.domain.Project;

public class AssignmentService {

    public boolean assign(Assignment assignment) {
        Project project = assignment.getProject();
        boolean suitable = assignment.getEmployee().isSuitableFor(project);

        return suitable && project.assign(assignment);
    }
}
