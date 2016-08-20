package com.thoughtworks.jigsaw.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "assignments", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
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

    public Assignment() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }
}
