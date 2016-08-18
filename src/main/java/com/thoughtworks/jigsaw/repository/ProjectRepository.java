package com.thoughtworks.jigsaw.repository;

import com.thoughtworks.jigsaw.domain.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long> {
}
