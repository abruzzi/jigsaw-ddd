package com.thoughtworks.jigsaw.utils;

import com.thoughtworks.jigsaw.domain.Project;
import com.thoughtworks.jigsaw.domain.Technical;

import java.util.ArrayList;
import java.util.Collections;

public class ProjectBuilder {
    private Project project;

    public ProjectBuilder() {
        project = new Project();
        project.setTechStack(new ArrayList<>());
        project.setAssignments(new ArrayList<>());
    }

    public ProjectBuilder name(String name) {
        project.setName(name);
        return this;
    }

    public ProjectBuilder technical(Technical technical) {
        project.getTechStack().add(technical);
        return this;
    }

    public ProjectBuilder technicals(Technical ...technicals) {
        Collections.addAll(project.getTechStack(), technicals);
        return this;
    }

    public Project build() {
        return this.project;
    }
}
