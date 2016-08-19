package com.thoughtworks.jigsaw.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "projectId")
    private List<Technical> techStack;

    private String name;
    private String client;
    private String location;
    private int manMonth;
    private Date deadline;
    private String description;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Assignment> assignments;

    public Project(String name) {
        this.name = name;
    }

    public boolean withTechnical(Technical technical) {
        return techStack.stream().anyMatch(t -> t.getCategory().equals(technical.getCategory()) && t.getName().equals(technical.getName()));
    }
}
