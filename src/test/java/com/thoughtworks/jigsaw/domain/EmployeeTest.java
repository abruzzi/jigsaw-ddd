package com.thoughtworks.jigsaw.domain;

import com.thoughtworks.jigsaw.utils.Fixture;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class EmployeeTest {
    private final Fixture fixture = new Fixture();
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
        Project project = Fixture.prepareARubyProject();
        employee.setSkills(Collections.singletonList(new Skill("Java", "language", 5)));
        assertThat(employee.isSuitableFor(project), is(false));
    }

    @Test
    public void should_be_suitable_when_skills_are_matching() {
        Project project = Fixture.prepareARubyProject();
        employee.setSkills(Collections.singletonList(new Skill("Ruby", "language", 5)));
        assertThat(employee.isSuitableFor(project), is(true));
    }
}