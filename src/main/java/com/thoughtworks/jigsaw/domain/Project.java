package com.thoughtworks.jigsaw.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Project {
    @Getter @Setter
    private List<Technical> techStack;
    private String name;
    private String client;
    private String location;
    private int manMonth;
    private Date deadline;
    private String description;

    private Map<Role, Integer> staffingModel;

    public Project(String name) {
        this.name = name;
    }

    @Getter
    private List<Assignment> assignments;

    public boolean addAssignment(Assignment assignment) {
        if(this.assignments == null) {
            assignments = new ArrayList<>();
        }

        return assignments.add(assignment);
    }

    public boolean withTechnical(Technical technical) {
        return techStack.stream().anyMatch(t -> t.equals(technical));
    }
}
