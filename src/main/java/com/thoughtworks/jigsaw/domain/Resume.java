package com.thoughtworks.jigsaw.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "resumes")
@NoArgsConstructor
public class Resume {
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @OneToOne
    private Employee employee;
}
