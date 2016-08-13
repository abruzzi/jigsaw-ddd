package com.thoughtworks.jigsaw.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Employee {
    @Getter @Setter private Resume resume;
    @Getter @Setter private List<Skill> skills;
}
