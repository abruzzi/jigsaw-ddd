package com.thoughtworks.jigsaw.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
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

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "employeeId")
    private List<Skill> skills;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Assignment> assignments;

    @Transient
    @Getter(AccessLevel.NONE)
    private Assignment currentAssignment;

    public Assignment getCurrentAssignment() {
        if(assignments == null) {
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
}
