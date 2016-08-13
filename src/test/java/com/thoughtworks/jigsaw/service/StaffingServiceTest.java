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

        Employee juntao = new Employee("Juntao Qiu");
        List<Skill> juntaoSkills = Collections.singletonList(
                new Skill(new Technical("JavaScript", "language"), 5)
        );
        juntao.setSkills(juntaoSkills);

        employees.add(juntao);

        Employee dong = new Employee("Dong Yang");
        List<Skill> dongSkills = Collections.singletonList(new Skill(new Technical("Java", "language"), 5));
        dong.setSkills(dongSkills);
        dong.setCurrentProject(new Project());

        employees.add(dong);

        Employee xiaofeng = new Employee("Xiaofeng Wang");
        List<Skill> xiaofengSkills = Collections.singletonList(new Skill(new Technical("Ruby", "language"), 5));
        xiaofeng.setSkills(xiaofengSkills);

        employees.add(xiaofeng);

        Employee huan = new Employee("Huan Wang");
        List<Skill> huanSkills = Collections.singletonList(new Skill(new Technical("Java", "language"), 5));
        huan.setSkills(huanSkills);

        employees.add(huan);

        Employee xiaochong = new Employee("Xiaochong Zhang");
        List<Skill> xiaochongSkills = Collections.singletonList(new Skill(new Technical("Rails", "framework"), 5));
        xiaochong.setSkills(xiaochongSkills);

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

        Project project = new Project();
        project.setTechStack(techStack);

        Iterable<Employee> iterable = staffingService.suitableEmployeesForProject(project);
        List<Employee> assignableEmployees = Lists.newArrayList(iterable);

        assertThat(assignableEmployees.size(), is(2));

        Employee rubist = assignableEmployees.get(0);
        assertThat(rubist.getName(), is("Xiaofeng Wang"));
    }
}
