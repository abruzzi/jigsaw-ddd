package com.thoughtworks.jigsaw.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    private String name;
    private String gradle;

    public Role(String name, String gradle) {
        this.name = name;
        this.gradle = gradle;
    }
}
