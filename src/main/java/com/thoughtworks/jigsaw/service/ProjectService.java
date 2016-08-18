package com.thoughtworks.jigsaw.service;

import com.thoughtworks.jigsaw.domain.Project;
import com.thoughtworks.jigsaw.domain.Technical;
import com.thoughtworks.jigsaw.repository.ProjectRepository;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ProjectService {
    private ProjectRepository projectRepository;
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    private Stream<Project> streamify(Iterable<Project> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    public Iterable<Project> projectsFor(Technical technical) {
        Iterable<Project> all = projectRepository.findAll();
        return streamify(all).filter(project -> project.withTechnical(technical)).collect(Collectors.toList());
    }
}
