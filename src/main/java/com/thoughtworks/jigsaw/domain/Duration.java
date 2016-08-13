package com.thoughtworks.jigsaw.domain;

import lombok.Getter;

import java.util.Date;

public class Duration {
    @Getter
    private Date from;

    @Getter
    private Date to;

    public Duration(Date from, Date to) {
        this.from = from;
        this.to = to;
    }
}
