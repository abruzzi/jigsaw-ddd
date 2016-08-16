package com.thoughtworks.jigsaw.repository;

import com.thoughtworks.jigsaw.domain.Assignment;
import com.thoughtworks.jigsaw.domain.Employee;
import com.thoughtworks.jigsaw.domain.Project;

import java.util.Date;

public interface AssignmentRepository {
    Iterable<Assignment> findByEmployeeAndEndDateBefore(Employee employee, Date date);
    Iterable<Assignment> findByProject(Project project);
    Iterable<Assignment> findByEmployee(Employee employee);
    
    boolean save(Assignment assignment);
}
