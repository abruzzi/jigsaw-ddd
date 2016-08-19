package com.thoughtworks.jigsaw.repository;


import com.thoughtworks.jigsaw.domain.Project;
import com.thoughtworks.jigsaw.domain.Technical;
import com.thoughtworks.jigsaw.utils.ProjectBuilder;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectRepositoryTest {
    @Autowired
    private ProjectRepository projectRepository;

    @Before
    public void setUp() {
        projectRepository.deleteAll();
        projectRepository.save(prepareProjects());
    }

    @Test
    public void should_return_projects_by_technical() {
        Iterable<Project> projects = projectRepository.findByTechnical(new Technical("Java", "language"));
        ArrayList<Project> projectsForJava = Lists.newArrayList(projects);
        assertThat(projectsForJava.size(), is(2));
    }

    private Iterable<Project> prepareProjects() {
        Project twu = new ProjectBuilder().
                name("ThoughtWorks University").
                technical(new Technical("Java", "language")).
                build();

        Project nepsd = new ProjectBuilder().
                name("NEP-SD Web").
                technicals(new Technical("Java", "language"), new Technical("JavaScript", "language")).
                build();

        Project twr = new ProjectBuilder().
                name("ThoughtWorks Recruting").
                technical(new Technical("Ruby", "language")).
                build();

        Project liveText = new ProjectBuilder().
                name("LiveText").
                technicals(new Technical("Ruby", "language"), new Technical("Rails", "framework")).
                build();

        Project reporting = new ProjectBuilder().
                name("Reporting").
                technicals(new Technical("Ruby", "language"), new Technical("Rails", "framework")).
                build();

        return Arrays.asList(twu, nepsd, twr, liveText, reporting);
    }

}