package com.thoughtworks.jigsaw.domain;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    private String name;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private Resume resume;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private Role role;

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "employeeId")
    private List<Skill> skills;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Assignment> assignments;

    @Transient
    private Assignment currentAssignment;
    private String homeOffice;
    private String travelPreference;

    public Assignment getCurrentAssignment() {
        if(assignments == null || assignments.size() == 0) {
            currentAssignment = null;
        } else {
            List<Assignment> cloned = new ArrayList<>(assignments);
            cloned.sort((o1, o2) -> o1.getEndAt().compareTo(o2.getEndAt()));
            currentAssignment = cloned.get(0);
        }

        return currentAssignment;
    }

    public boolean isOnTheBeach() {
        return currentAssignment != null &&
                currentAssignment.getProject().getName().equals("ThoughtWorks Core");
    }

    public Employee(String name) {
        this.name = name;
    }

    public boolean isSuitableFor(Project project) {
        return skills.stream().anyMatch(
                skill -> project.getTechStack().stream().anyMatch(
                        technical -> technical.getCategory().equals(skill.getCategory()) &&
                                technical.getName().equals(skill.getName()))
        );
    }

    public boolean isAssignable() {
        return getCurrentAssignment() == null || isOnTheBeach();
    }

    public Employee() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    public void setCurrentAssignment(Assignment currentAssignment) {
        this.currentAssignment = currentAssignment;
    }


    public void setHomeOffice(String homeOffice) {
        this.homeOffice = homeOffice;
    }

    public String getHomeOffice() {
        return homeOffice;
    }

    public void setTravelPreference(String travelPreference) {
        this.travelPreference = travelPreference;
    }

    public String getTravelPreference() {
        return travelPreference;
    }
}
