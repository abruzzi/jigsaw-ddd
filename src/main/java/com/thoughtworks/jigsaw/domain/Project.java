package com.thoughtworks.jigsaw.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
@Table(name = "projects")
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

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

    public Project() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Technical> getTechStack() {
        return techStack;
    }

    public void setTechStack(List<Technical> techStack) {
        this.techStack = techStack;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getManMonth() {
        return manMonth;
    }

    public void setManMonth(int manMonth) {
        this.manMonth = manMonth;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    public Map<Role, Integer> getStaffingModel() {
        return staffingModel;
    }

    public void setStaffingModel(Map<Role, Integer> staffingModel) {
        this.staffingModel = staffingModel;
    }
}
