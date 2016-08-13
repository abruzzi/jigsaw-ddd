package com.thoughtworks.jigsaw.domain;

import lombok.Getter;

public class Skill {
    @Getter
    private Technical technical;
    @Getter
    private int rating;

    public Skill(Technical technical, int rating) {
        this.technical = technical;
        this.rating = rating;
    }
}
