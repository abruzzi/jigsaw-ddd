package com.thoughtworks.jigsaw.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class EmployeeTest {
    private Employee employee;

    @Before
    public void setUp() {
        employee = new Employee("Juntao Qiu");
    }

    @Test
    public void should_be_assignable_when_no_current_assignment() {
        employee.setAssignments(null);
        assertThat(employee.isAssignable(), is(true));
    }

    @Test
    public void should_be_assignable_when_on_the_beach() {
        Date now = new Date();
        employee.setAssignments(Collections.singletonList(new Assignment(new Project("ThoughtWorks Core"), employee, now, now)));
        assertThat(employee.isAssignable(), is(true));
    }

    @Test
    public void should_not_be_suitable_when_skills_are_not_matching() {
        employee.setSkills(Collections.singletonList(new Skill("Java", "language", 5)));
        Project project = new Project("Rails Boys");
        project.setTechStack(Collections.singletonList(new Technical("Ruby", "language")));

        assertThat(employee.isSuitableFor(project), is(false));
    }

    @Test
    public void should_be_suitable_when_skills_are_matching() {
        employee.setSkills(Collections.singletonList(new Skill("Ruby", "language", 5)));
        Project project = new Project("Rails Boys");
        project.setTechStack(Collections.singletonList(new Technical("Ruby", "language")));

        assertThat(employee.isSuitableFor(project), is(true));
    }
}