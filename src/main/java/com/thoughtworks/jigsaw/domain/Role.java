package com.thoughtworks.jigsaw.domain;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    private String name;
    private String gradle;

    @OneToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;

    public Role(String name, String gradle) {
        this.name = name;
        this.gradle = gradle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;

        Role role = (Role) o;

        if (!name.equals(role.name)) return false;
        return gradle.equals(role.gradle);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + gradle.hashCode();
        return result;
    }

    public Role() {
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

    public String getGradle() {
        return gradle;
    }

    public void setGradle(String gradle) {
        this.gradle = gradle;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
