package com.thoughtworks.jigsaw.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Project {
    @Getter @Setter
    private List<Technical> techStack;
    private String client;
    private String location;
    private int manMonth;
    private Date deadline;
    private String description;

    private Map<Role, Integer> staffingModel;

    @Getter
    @Setter
    private List<Assignment> assignments;
}
