package com.thoughtworks.jigsaw.repository;

import com.thoughtworks.jigsaw.domain.Assignment;
import com.thoughtworks.jigsaw.domain.Employee;
import com.thoughtworks.jigsaw.domain.Project;
import org.springframework.data.repository.CrudRepository;

public interface AssignmentRepository extends CrudRepository<Assignment, Long> {
    Iterable<Assignment> findByProject(Project project);
    Iterable<Assignment> findByEmployee(Employee employee);
}
