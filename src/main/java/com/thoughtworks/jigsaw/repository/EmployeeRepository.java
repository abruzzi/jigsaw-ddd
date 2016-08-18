package com.thoughtworks.jigsaw.repository;

import com.thoughtworks.jigsaw.domain.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
