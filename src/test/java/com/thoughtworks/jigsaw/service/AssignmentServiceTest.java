package com.thoughtworks.jigsaw.service;

import com.thoughtworks.jigsaw.domain.Assignment;
import com.thoughtworks.jigsaw.domain.Employee;
import com.thoughtworks.jigsaw.domain.Project;
import com.thoughtworks.jigsaw.exception.CannotTravelException;
import com.thoughtworks.jigsaw.exception.IsNotAssignableException;
import com.thoughtworks.jigsaw.exception.IsNotSuitableException;
import com.thoughtworks.jigsaw.repository.AssignmentRepository;
import com.thoughtworks.jigsaw.repository.EmployeeRepository;
import com.thoughtworks.jigsaw.repository.ProjectRepository;
import com.thoughtworks.jigsaw.utils.Fixture;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("integration")
public class AssignmentServiceTest {
    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Before
    public void setUp() {
        employeeRepository.deleteAll();
        projectRepository.deleteAll();
        assignmentRepository.deleteAll();
    }

    @Test(expected = IsNotSuitableException.class)
    public void should_not_assign_java_dev_to_ruby_project() throws ParseException, CannotTravelException, IsNotAssignableException, IsNotSuitableException {
        Project project = Fixture.prepareARubyProject();
        Employee dev = Fixture.prepareAJavaDev();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        assignmentService.assign(dev, project, format.parse("2016-08-08"), format.parse("2016-09-08"));
    }

    @Test
    public void should_assign_ruby_dev_to_ruby_project() throws ParseException, CannotTravelException, IsNotAssignableException, IsNotSuitableException {
        Project project = Fixture.prepareARubyProject();
        Employee dev = Fixture.prepareARubyDev();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Assignment assignment = assignmentService.assign(dev, project, format.parse("2016-08-08"), format.parse("2016-09-08"));

        assertThat(assignment.getEmployee().getName(), is(dev.getName()));
        assertThat(assignment.getProject().getName(), is(project.getName()));
    }

    @Test(expected = CannotTravelException.class)
    public void should_not_assign_when_java_dev_can_want_to_travel() throws ParseException, CannotTravelException, IsNotAssignableException, IsNotSuitableException {
        Project project = Fixture.prepareARubyProject();
        Employee dev = Fixture.prepareARubyDev();

        project.setLocation("Shenzhen");
        dev.setHomeOffice("Beijing");
        dev.setTravelPreference("no-travel");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        assignmentService.assign(dev, project, format.parse("2016-08-08"), format.parse("2016-09-08"));
    }
}
