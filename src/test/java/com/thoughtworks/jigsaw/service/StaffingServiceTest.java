package com.thoughtworks.jigsaw.service;

import com.thoughtworks.jigsaw.domain.Employee;
import com.thoughtworks.jigsaw.domain.Project;
import com.thoughtworks.jigsaw.domain.Skill;
import com.thoughtworks.jigsaw.domain.Technical;
import com.thoughtworks.jigsaw.repository.EmployeeRepository;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
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
        staffingService = new StaffingService(employeeRepository);
    }

    private List<Employee> prepareEmployees() throws ParseException {
        ArrayList<Employee> employees = new ArrayList<>();

        Employee juntao = prepareEmployee("Juntao Qiu", "JavaScript", "language");
        employees.add(juntao);

        Employee dong = prepareEmployee("Dong Yang", "Java", "language");
        dong.setCurrentProject(new Project("HomeFlight of India"));
        employees.add(dong);

        Employee xiaofeng = prepareEmployee("Xiaofeng Wang", "Ruby", "language");
        employees.add(xiaofeng);

        Employee huan = prepareEmployee("Huan Wang", "Java", "language");
        employees.add(huan);

        Employee xiaochong = prepareEmployee("Xiaochong Zhang", "Rails", "framework");
        employees.add(xiaochong);

        return employees;
    }

    private Employee prepareEmployee(String name, String technology, String category) {
        Employee employee = new Employee(name);
        List<Skill> skills = Collections.singletonList(new Skill(new Technical(technology, category), 5));
        employee.setSkills(skills);
        return employee;
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
}
