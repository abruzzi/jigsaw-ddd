package com.thoughtworks.jigsaw;

import com.thoughtworks.jigsaw.domain.*;
import com.thoughtworks.jigsaw.repository.AssignmentRepository;
import com.thoughtworks.jigsaw.repository.EmployeeRepository;
import com.thoughtworks.jigsaw.repository.ProjectRepository;
import com.thoughtworks.jigsaw.repository.TechnicalRepository;
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

	@Autowired
	private TechnicalRepository technicalRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner run() {
		return (args) -> {
			Technical java = new Technical("Java", "language");
			Technical spring = new Technical("Spring", "framework");

			Project nepsd = new Project("NEP-SD Web");
			nepsd.setTechStack(Arrays.asList(java, spring));

			Project saved = projectRepository.save(nepsd);

			java.setProject(saved);
			spring.setProject(saved);

			technicalRepository.save(Arrays.asList(java, spring));

			Employee juntao = new Employee("Juntao Qiu");
			juntao.setSkills(Collections.singletonList(new Skill("Java", "language", 5)));
			employeeRepository.save(juntao);

			Date today = new Date();
			Assignment assignment = new Assignment(nepsd, juntao, today, today);
			assignmentRepository.save(assignment);

			Iterable<Project> byTechnical = projectRepository.findByTechnical(java);
//			System.err.println(saved);
			byTechnical.forEach(x -> System.err.println(x.getName()));
		};
	}

}
