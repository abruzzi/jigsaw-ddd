package com.thoughtworks.jigsaw.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

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

    @Transient
    private Map<Role, Integer> staffingModel;

    public Project(String name) {
        this.name = name;
    }

    public Map<Role, Integer> openRoles() {
        if(assignments == null) {
            return staffingModel;
        }

        List<Role> roles = assignments.stream().map(x -> x.getEmployee().getRole()).collect(Collectors.toList());
        HashMap<Role, Integer> openRoles = new HashMap<>();

        roles.forEach(role -> {
            if(staffingModel.containsKey(role)) {
                openRoles.put(role, staffingModel.get(role)-1);
            } else {
                openRoles.put(role, 1);
            }
        });

        return openRoles;
    }
}
