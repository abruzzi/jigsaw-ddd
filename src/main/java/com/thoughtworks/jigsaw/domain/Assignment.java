package com.thoughtworks.jigsaw.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "assignments", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
@Data
@NoArgsConstructor
public class Assignment {
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeId")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projectId")
    private Project project;

    private Date startAt;
    private Date endAt;

    public Assignment(Project project, Employee employee, Date startAt, Date endAt) {
        this.project = project;
        this.employee = employee;
        this.startAt = startAt;
        this.endAt = endAt;
    }

}
