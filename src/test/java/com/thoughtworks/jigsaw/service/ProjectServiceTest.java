package com.thoughtworks.jigsaw.service;

import com.thoughtworks.jigsaw.domain.Project;
import com.thoughtworks.jigsaw.domain.Technical;
import com.thoughtworks.jigsaw.repository.ProjectRepository;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
        Project twu = new Project("ThoughtWorks University");
        twu.setTechStack(Collections.singletonList(new Technical("Java", "language")));

        Project nepsd = new Project("NEP-SD Web");
        nepsd.setTechStack(Arrays.asList(new Technical("Java", "language"), new Technical("JavaScript", "language")));

        Project twr = new Project("ThoughtWorks Recruting");
        twr.setTechStack(Collections.singletonList(new Technical("Ruby", "language")));

        Project liveText = new Project("LiveText");
        liveText.setTechStack(Arrays.asList(new Technical("Ruby", "language"), new Technical("Rails", "framework")));

        Project reporting = new Project("Reporting");
        reporting.setTechStack(Arrays.asList(new Technical("Ruby", "language"), new Technical("Rails", "framework")));

        return Arrays.asList(twu, nepsd, twr, liveText, reporting);
    }

    @Test
    public void should_return_projects_by_technical() {
        Iterable<Project> projects = projectService.projectsFor(new Technical("Java", "language"));
        ArrayList<Project> projectsForJava = Lists.newArrayList(projects);
        assertThat(projectsForJava.size(), is(2));
    }
}
