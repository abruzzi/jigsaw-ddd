package com.thoughtworks.jigsaw.service;

import com.thoughtworks.jigsaw.domain.Project;
import com.thoughtworks.jigsaw.domain.Technical;
import com.thoughtworks.jigsaw.repository.ProjectRepository;
import com.thoughtworks.jigsaw.utils.ProjectBuilder;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectServiceTest {
    private ProjectService projectService;

    @Before
    public void setUp() {
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        when(projectRepository.findAll()).thenReturn(prepareProjects());
        projectService = new ProjectService(projectRepository);
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

    @Test
    public void should_return_projects_by_technical() {
        Iterable<Project> projects = projectService.projectsFor(new Technical("Java", "language"));
        ArrayList<Project> projectsForJava = Lists.newArrayList(projects);
        assertThat(projectsForJava.size(), is(2));
    }
}
