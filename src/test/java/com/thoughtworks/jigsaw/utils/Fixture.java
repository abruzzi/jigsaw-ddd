package com.thoughtworks.jigsaw.utils;

import com.thoughtworks.jigsaw.domain.*;

public class Fixture {
    public Fixture() {
    }

    public static Project prepareARubyProject() {
        ProjectBuilder builder = new ProjectBuilder();
        return builder.
                name("Rails Boys").
                technical(new Technical("Ruby", "language")).
                location("Xian").
                build();
    }

    public static Employee prepareARubyDev() {
        EmployeeBuilder builder = new EmployeeBuilder();
        return builder.
                name("Ruby Dev").
                skill(new Skill("Ruby", "language", 5)).
                role(new Role("Dev", "Senior")).
                homeOffice("Xian").
                travelPreference("travel").
                build();
    }

    public static Employee prepareAJavaDev() {
        EmployeeBuilder builder = new EmployeeBuilder();
        return builder.
                name("Java Dev").
                skill(new Skill("Java", "language", 5)).
                role(new Role("Dev", "Senior")).
                homeOffice("Xian").
                travelPreference("travel").
                build();
    }
}