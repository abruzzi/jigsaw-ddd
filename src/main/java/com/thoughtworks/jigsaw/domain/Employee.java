package com.thoughtworks.jigsaw.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    private String name;

    @OneToOne(mappedBy = "employee")
    private Resume resume;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    private List<Skill> skills;

    @OneToOne
    private Assignment currentAssignment;

    public Employee(String name) {
        this.name = name;
    }

    public boolean isSuitableFor(Project project) {
        return skills.stream().anyMatch(
                technical -> project.getTechStack().stream().anyMatch(t -> t.equals(technical))
        );
    }
}
