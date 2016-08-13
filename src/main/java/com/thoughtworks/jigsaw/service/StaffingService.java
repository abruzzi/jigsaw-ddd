package com.thoughtworks.jigsaw.service;

import com.thoughtworks.jigsaw.domain.Employee;
import com.thoughtworks.jigsaw.repository.EmployeeRepository;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StaffingService {
    private EmployeeRepository employeeRepository;

    public StaffingService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Iterable<Employee> getAssignableEmployees() {
        Iterable<Employee> all = employeeRepository.findAll();
        return StreamSupport
                .stream(all.spliterator(), false)
                .filter((employee -> employee.getCurrentProject() != null))
                .collect(Collectors.toList());
    }
}
