package com.thoughtworks.jigsaw.repository;

import com.thoughtworks.jigsaw.domain.Employee;
import com.thoughtworks.jigsaw.domain.Skill;
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
public class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Before
    public void setUp() {
        employeeRepository.deleteAll();
        employeeRepository.save(prepareEmployees());
    }

    private Iterable<Employee> prepareEmployees() {
        Employee juntao = new Employee("Juntao");
        juntao.setSkills(Arrays.asList(new Skill("Java", "language", 5)));

        Employee xiaofeng = new Employee("Xiaofeng");
        xiaofeng.setSkills(Arrays.asList(new Skill("Ruby", "language", 5)));

        Employee dong = new Employee("Dong");
        dong.setSkills(Arrays.asList(new Skill("Java", "language", 5)));

        return Arrays.asList(juntao, xiaofeng, dong);
    }

    @Test
    public void should_know_how_many_people_has_a_specific_skill() {
        Skill skill = new Skill("Java", "language", 5);
        Iterable<Employee> bySkill = employeeRepository.findBySkill(skill);

        ArrayList<Employee> employees = Lists.newArrayList(bySkill);

        assertThat(employees.size(), is(2));
    }
}