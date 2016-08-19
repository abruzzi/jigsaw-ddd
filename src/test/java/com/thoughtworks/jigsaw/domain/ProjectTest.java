package com.thoughtworks.jigsaw.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProjectTest {
    private Project project;

    @Before
    public void setUp() {
        project = new Project("ThoughtWorks Core");
    }

    @Test
    public void should_tell_how_many_open_roles() {
        Map<Role, Integer> staffingModel = Collections.unmodifiableMap(Stream.of(
                new AbstractMap.SimpleEntry<>(new Role("Dev", "Senior"), 3),
                new AbstractMap.SimpleEntry<>(new Role("QA", "Senior"), 1)
        ).collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue)));

        project.setStaffingModel(staffingModel);
        assertThat(project.openRoles(), notNullValue());
    }
}