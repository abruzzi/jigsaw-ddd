package com.thoughtworks.jigsaw.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Employee {
    @Getter private String name;
    @Getter @Setter private Resume resume;
    @Getter @Setter private List<Skill> skills;
    @Getter @Setter private Project currentProject;

    public Employee(String name) {
        this.name = name;
    }

    public boolean isSuitableFor(Project project) {
        return skills.stream().map(Skill::getTechnical).anyMatch(
                technical -> project.getTechStack().stream().anyMatch(t -> t.equals(technical))
        );
    }
}
