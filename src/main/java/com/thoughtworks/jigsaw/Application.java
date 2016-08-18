package com.thoughtworks.jigsaw;

import com.thoughtworks.jigsaw.domain.*;
import com.thoughtworks.jigsaw.repository.AssignmentRepository;
import com.thoughtworks.jigsaw.repository.EmployeeRepository;
import com.thoughtworks.jigsaw.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

@SpringBootApplication
public class Application {

	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private AssignmentRepository assignmentRepository;
	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner run() {
		return (args) -> {
			Technical java = new Technical("Java", "language");
			Technical ruby = new Technical("Ruby", "language");

			Project project = new Project("NEP-SD Web");
			project.setTechStack(Arrays.asList(java, ruby));

			Project saved = projectRepository.save(project);

			Employee juntao = new Employee("Juntao Qiu");
			juntao.setSkills(Collections.singletonList(new Skill("Java", "language", 5)));
			employeeRepository.save(juntao);

			Date today = new Date();
			Assignment assignment = new Assignment(project, juntao, today, today);
			assignmentRepository.save(assignment);

//			System.err.println(projectRepository.findOne(saved.getId()));
		};
	}

}
