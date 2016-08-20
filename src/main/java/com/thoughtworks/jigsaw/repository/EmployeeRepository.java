package com.thoughtworks.jigsaw.repository;

import com.thoughtworks.jigsaw.domain.Employee;
import com.thoughtworks.jigsaw.domain.Skill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Iterable<Employee> findByName(String name);

    @Query("select e from Employee e join e.skills as skills where skills.name = :#{#skill.name}")
    Iterable<Employee> findBySkill(@Param("skill") Skill skill);
}
