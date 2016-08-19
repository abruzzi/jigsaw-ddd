package com.thoughtworks.jigsaw.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Role {
    private String name;
    private String gradle;

    public Role(String name, String gradle) {
        this.name = name;
        this.gradle = gradle;
    }
}
