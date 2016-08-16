package com.thoughtworks.jigsaw.domain;

import lombok.Getter;

public class Assignment {
    @Getter
    private Employee employee;

    @Getter
    private Project project;

    @Getter
    private Duration duration;

    public Assignment(Project project, Employee employee, Duration duration) {
        this.project = project;
        this.employee = employee;
        this.duration = duration;
    }

    public void updateRelations() {
        employee.setCurrentAssignment(this);
        project.addAssignment(this);
    }
}
