package com.thoughtworks.jigsaw.repository;

import com.thoughtworks.jigsaw.domain.Project;
import com.thoughtworks.jigsaw.domain.Technical;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProjectRepository extends CrudRepository<Project, Long> {
    @Query("select p from Project p join p.techStack as stack where stack.name = :#{#technical.name}")
    Iterable<Project> findByTechnical(@Param("technical") Technical technical);
}
