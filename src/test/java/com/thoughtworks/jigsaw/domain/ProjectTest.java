package com.thoughtworks.jigsaw.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ProjectTest {
    private Project project;

    @Before
    public void setUp() {
        project = new Project("ThoughtWorks Core");
    }

    @Test
    public void should_tell_how_many_open_roles() {
        Role seniorDev = new Role("Dev", "Senior");
        Role seniorQA = new Role("QA", "Senior");

        Map<Role, Integer> staffingModel = prepareStaffingModel(seniorDev, seniorQA);

        project.setStaffingModel(staffingModel);
        Map<Role, Integer> actual = project.openRoles();

        assertThat(actual.get(seniorDev), is(3));
        assertThat(actual.get(seniorQA), is(1));
    }

    @Test
    public void should_tell_how_many_open_roles_left_after_assignments() {
        Role seniorDev = new Role("Dev", "Senior");
        Role seniorQA = new Role("QA", "Senior");

        Employee juntao = new Employee("Juntao Qiu");
        juntao.setRole(seniorDev);

        Employee yong = new Employee("Yong Huang");
        yong.setRole(seniorQA);

        Map<Role, Integer> staffingModel = prepareStaffingModel(seniorDev, seniorQA);

        Date date = new Date();
        project.setStaffingModel(staffingModel);
        project.setAssignments(Arrays.asList(new Assignment(project, juntao, date, date), new Assignment(project, yong, date, date)));

        Map<Role, Integer> actual = project.openRoles();
        assertThat(actual.get(seniorDev), is(2));
        assertThat(actual.get(seniorQA), is(0));
    }

    private Map<Role, Integer> prepareStaffingModel(Role seniorDev, Role seniorQA) {
        return Collections.unmodifiableMap(Stream.of(
                    new AbstractMap.SimpleEntry<>(seniorDev, 3),
                    new AbstractMap.SimpleEntry<>(seniorQA, 1)
            ).collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue)));
    }
}