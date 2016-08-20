package com.thoughtworks.jigsaw.domain;

import com.thoughtworks.jigsaw.utils.EmployeeBuilder;
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

        Map<Role, Integer> staffingModel = prepareStaffingModel();

        project.setStaffingModel(staffingModel);
        Map<Role, Integer> actual = project.openRoles();

        assertThat(actual.get(seniorDev), is(3));
        assertThat(actual.get(seniorQA), is(1));
    }

    @Test
    public void should_tell_how_many_open_roles_left_after_assignments() {
        EmployeeBuilder b1 = new EmployeeBuilder();
        Employee juntao = b1.name("Juntao Qiu").role(new Role("Dev", "Senior")).build();

        EmployeeBuilder b2 = new EmployeeBuilder();
        Employee yong = b2.name("Yong Huang").role(new Role("QA", "Senior")).build();

        Map<Role, Integer> staffingModel = prepareStaffingModel();

        Date date = new Date();
        project.setStaffingModel(staffingModel);
        project.setAssignments(Arrays.asList(new Assignment(project, juntao, date, date), new Assignment(project, yong, date, date)));

        Map<Role, Integer> actual = project.openRoles();
        assertThat(actual.get(new Role("Dev", "Senior")), is(2));
        assertThat(actual.get(new Role("QA", "Senior")), is(0));
    }

    private Map<Role, Integer> prepareStaffingModel() {
        Role seniorDev = new Role("Dev", "Senior");
        Role seniorQA = new Role("QA", "Senior");

        return Collections.unmodifiableMap(Stream.of(
                    new AbstractMap.SimpleEntry<>(seniorDev, 3),
                    new AbstractMap.SimpleEntry<>(seniorQA, 1)
            ).collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue)));
    }
}