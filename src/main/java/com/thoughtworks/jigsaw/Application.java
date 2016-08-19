package com.thoughtworks.jigsaw;

import com.thoughtworks.jigsaw.repository.AssignmentRepository;
import com.thoughtworks.jigsaw.repository.EmployeeRepository;
import com.thoughtworks.jigsaw.repository.ProjectRepository;
import com.thoughtworks.jigsaw.repository.TechnicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

//	@Bean
//	public CommandLineRunner run() {
//		return (args) -> {
//			Technical java = new Technical("Java", "language");
////			Technical spring = new Technical("Spring", "framework");
////
////			Project nepsd = new Project("NEP-SD Web");
////			nepsd.setTechStack(Arrays.asList(java, spring));
////
////			Project saved = projectRepository.save(nepsd);
////
////			technicalRepository.save(Arrays.asList(java, spring));
////
////			Employee juntao = new Employee("Juntao Qiu");
////			juntao.setSkills(Collections.singletonList(new Skill("Java", "language", 5)));
////			employeeRepository.save(juntao);
////
////			Date today = new Date();
////			Assignment assignment = new Assignment(nepsd, juntao, today, today);
////			assignmentRepository.save(assignment);
//
//			Iterable<Project> byTechnical = projectRepository.findByTechnical(java);
//			byTechnical.forEach(x -> System.err.println(x.getName()));
//
//			Iterable<Employee> byName = employeeRepository.findByName("Juntao Qiu");
//			byName.forEach(x -> System.err.println(x.getName()));
//
//			byName.forEach(x -> System.err.println(x.getSkills()));
//			byName.forEach(x -> {
//				Assignment currentAssignment = x.getCurrentAssignment();
//				System.err.println(currentAssignment.getStartAt());
//				System.err.println(currentAssignment.getEndAt());
//			});
//		};
//	}

}
