package com.thoughtworks.jigsaw.utils;

import com.thoughtworks.jigsaw.domain.Project;
import com.thoughtworks.jigsaw.domain.Technical;

import java.util.Collections;

public class ProjectBuilder {
    private Project project;

    /*

    Project twu = new Project("ThoughtWorks University");
        twu.setTechStack(Collections.singletonList(new Technical("Java", "language")));

        Project nepsd = new Project("NEP-SD Web");
        nepsd.setTechStack(Arrays.asList(new Technical("Java", "language"), new Technical("JavaScript", "language")));

        Project twr = new Project("ThoughtWorks Recruting");
        twr.setTechStack(Collections.singletonList(new Technical("Ruby", "language")));

        Project liveText = new Project("LiveText");
        liveText.setTechStack(Arrays.asList(new Technical("Ruby", "language"), new Technical("Rails", "framework")));

        Project reporting = new Project("Reporting");
        reporting.setTechStack(Arrays.asList(new Technical("Ruby", "language"), new Technical("Rails", "framework")));

        return Arrays.asList(twu, nepsd, twr, liveText, reporting);
     */

    public ProjectBuilder name(String name) {
        project.setName(name);
        return this;
    }

    public ProjectBuilder technical(Technical technical) {
        project.getTechStack().add(technical);
        return this;
    }

    public ProjectBuilder technicals(Technical ...technicals) {
        Collections.addAll(project.getTechStack(), technicals);
        return this;
    }

    public Project build() {
        return this.project;
    }
}
