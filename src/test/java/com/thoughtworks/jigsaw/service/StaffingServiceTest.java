package com.thoughtworks.jigsaw.service;

import com.thoughtworks.jigsaw.domain.*;
import com.thoughtworks.jigsaw.repository.AssignmentRepository;
import com.thoughtworks.jigsaw.repository.EmployeeRepository;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StaffingServiceTest {
    private StaffingService staffingService;
    @Before
    public void setUp() throws ParseException {
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        when(employeeRepository.findAll()).thenReturn(prepareEmployees());

        AssignmentRepository assignmentRepository = mock(AssignmentRepository.class);
        when(assignmentRepository.findByEmployeeAndEndDateBefore(any(Employee.class), any(Date.class))).thenReturn(prepareAssignments());
        when(assignmentRepository.findByProject(any(Project.class))).thenReturn(Collections.singletonList(prepareAssignmentFor("ThoughtWorks Core")));

        staffingService = new StaffingService(employeeRepository, assignmentRepository);
    }

    private Assignment prepareAssignmentFor(String projectName) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return new Assignment(new Project(projectName), new Employee("Someone"), new Duration(simpleDateFormat.parse("2016-08-08"), simpleDateFormat.parse("2016-09-08"));
    }

    private Iterable<Assignment> prepareAssignments() throws ParseException {
        return Collections.singletonList(prepareAssignmentFor(null));
    }

    private List<Employee> prepareEmployees() throws ParseException {
        ArrayList<Employee> employees = new ArrayList<>();

        Employee juntao = prepareEmployee("Juntao Qiu", "JavaScript", "language");
        employees.add(juntao);

        Employee dong = prepareEmployee("Dong Yang", "Java", "language");
        dong.setCurrentAssignment(prepareAssignmentFor("HomeFlight of India"));
        employees.add(dong);

        Employee xiaofeng = prepareEmployee("Xiaofeng Wang", "Ruby", "language");
        employees.add(xiaofeng);

        Employee huan = prepareEmployee("Huan Wang", "Java", "language");
        employees.add(huan);

        Employee xiaochong = prepareEmployee("Xiaochong Zhang", "Rails", "framework");
        employees.add(xiaochong);

        return employees;
    }

    @Test
    public void should_get_assignable_employees_when_they_are_not_on_any_projects() {
        Iterable<Employee> iterable = staffingService.getAssignableEmployees();
        List<Employee> assignableEmployees = Lists.newArrayList(iterable);
        assertThat(assignableEmployees.size(), is(4));

        Employee employee = assignableEmployees.get(0);
        assertThat(employee.getName(), is("Juntao Qiu"));
    }

    @Test
    public void should_return_rubist_for_project_rubymine() {
        List<Technical> techStack = Arrays.asList(
                new Technical("Ruby", "language"),
                new Technical("Rails", "framework"));

        Project project = new Project("Fake Project");
        project.setTechStack(techStack);

        Iterable<Employee> iterable = staffingService.suitableEmployeesForProject(project);
        List<Employee> assignableEmployees = Lists.newArrayList(iterable);

        assertThat(assignableEmployees.size(), is(2));

        Employee rubist = assignableEmployees.get(0);
        assertThat(rubist.getName(), is("Xiaofeng Wang"));
    }

    @Test
    public void should_assign_right_people_on_project() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Project project = prepareProject();
        Assignment assignment = new Assignment(
                project,
                prepareEmployee("Juntao Qiu", "Ruby", "language"),
                new Duration(simpleDateFormat.parse("2016-08-08"), simpleDateFormat.parse("2016-09-08")));

        staffingService.assign(assignment);
        List<Assignment> assignments = project.getAssignments();

        assertThat(assignments.size(), is(1));
        assertThat(assignments.get(0).getEmployee().getName(), is("Juntao Qiu"));
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

        Project project = new Project("Fake Project");
        project.setTechStack(techStack);

        return project;
    }
}
