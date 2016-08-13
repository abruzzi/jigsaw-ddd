package com.thoughtworks.jigsaw.service;

import com.thoughtworks.jigsaw.domain.Employee;
import com.thoughtworks.jigsaw.repository.EmployeeRepository;

public class StaffingService {
    private EmployeeRepository employeeRepository;

    public StaffingService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public Iterable<Employee> getAssignableEmployees() {
        return employeeRepository.findAll();
    }
}
