package com.thoughtworks.jigsaw.repository;

import com.thoughtworks.jigsaw.domain.Employee;

public interface EmployeeRepository {
    Iterable<Employee> findAll();
}
