package com.thoughtworks.jigsaw;

import com.thoughtworks.jigsaw.domain.Project;
import com.thoughtworks.jigsaw.domain.Technical;
import com.thoughtworks.jigsaw.repository.ProjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner run(ProjectRepository repository) {
		return (args) -> {
			Technical java = new Technical("Java", "language");
			Technical ruby = new Technical("Ruby", "language");

			Project project = new Project("NEP-SD Web");
			project.setTechStack(Arrays.asList(java, ruby));

			repository.save(project);

			System.err.println(repository.findAll());
		};
	}

}
