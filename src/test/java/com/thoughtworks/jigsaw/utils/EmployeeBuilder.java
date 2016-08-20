package com.thoughtworks.jigsaw.utils;

import com.thoughtworks.jigsaw.domain.Assignment;
import com.thoughtworks.jigsaw.domain.Employee;
import com.thoughtworks.jigsaw.domain.Role;
import com.thoughtworks.jigsaw.domain.Skill;

import java.util.ArrayList;
import java.util.Arrays;

public class EmployeeBuilder {
    private Employee employee;

    public EmployeeBuilder() {
        employee = new Employee();
        employee.setSkills(new ArrayList<>());
        employee.setAssignments(new ArrayList<>());
    }

    public EmployeeBuilder name(String name) {
        employee.setName(name);
        return this;
    }

    public EmployeeBuilder role(Role role) {
        employee.setRole(role);
        return this;
    }
    public EmployeeBuilder skill(Skill skill) {
        employee.getSkills().add(skill);
        return this;
    }

    public EmployeeBuilder skills(Skill ...skills) {
        employee.setSkills(Arrays.asList(skills));
        return this;
    }

    public EmployeeBuilder assignment(Assignment assignment) {
        employee.getAssignments().add(assignment);
        return this;
    }

    public EmployeeBuilder assignments(Assignment ...assignments) {
        employee.setAssignments(Arrays.asList(assignments));
        return this;
    }

    public Employee build() {
        return employee;
    }
}
