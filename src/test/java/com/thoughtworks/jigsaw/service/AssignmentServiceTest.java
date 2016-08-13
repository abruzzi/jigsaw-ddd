package com.thoughtworks.jigsaw.service;

import com.thoughtworks.jigsaw.domain.*;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AssignmentServiceTest {
    private AssignmentService assignmentService;

    @Before
    public void setUp() {
        assignmentService = new AssignmentService();
    }

    private Employee prepareEmployee(String name, String technology, String category) {
        Employee employee = new Employee(name);
        List<Skill> skills = Collections.singletonList(new Skill(new Technical(technology, category), 5));
        employee.setSkills(skills);
        return employee;
    }

    private Project prepareProject() {
        List<Technical> techStack = Arrays.asList(
                new Technical("Ruby", "language"),
                new Technical("Rails", "framework"));

        Project project = new Project();
        project.setTechStack(techStack);

        return project;
    }

    @Test
    public void should_assign_right_people_on_project() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Project project = prepareProject();
        Assignment assignment = new Assignment(
                project,
                prepareEmployee("Juntao Qiu", "Ruby", "language"),
                new Duration(simpleDateFormat.parse("2016-08-08"), simpleDateFormat.parse("2016-09-08")));

        assignmentService.assign(assignment);
        List<Assignment> assignments = project.getAssignments();

        assertThat(assignments.size(), is(1));
        assertThat(assignments.get(0).getEmployee().getName(), is("Juntao Qiu"));
    }
}