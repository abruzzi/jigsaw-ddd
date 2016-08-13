package com.thoughtworks.jigsaw.service;

import com.thoughtworks.jigsaw.domain.Employee;
import com.thoughtworks.jigsaw.domain.Project;
import com.thoughtworks.jigsaw.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;
import static junit.framework.TestCase.assertEquals;
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
        employees.add(juntao);

        Employee dong = new Employee("Dong Yang");
        dong.setCurrentProject(new Project());

        employees.add(dong);
        return employees;
    }

    @Test
    public void should_known_assignable_employees() {
        Iterable<Employee> iterable = staffingService.getAssignableEmployees();
        List<Employee> assignableEmployees = StreamSupport.stream(iterable.spliterator(), false).collect(toList());
        assertEquals(assignableEmployees.size(), 1);
    }
}
