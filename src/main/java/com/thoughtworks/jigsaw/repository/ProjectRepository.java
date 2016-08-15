package com.thoughtworks.jigsaw.repository;

import com.thoughtworks.jigsaw.domain.Project;
import com.thoughtworks.jigsaw.domain.Technical;

public interface ProjectRepository {
    Iterable<Project> findAll();
    Iterable<Project> findProjectByTechnical(Technical technical);
}
