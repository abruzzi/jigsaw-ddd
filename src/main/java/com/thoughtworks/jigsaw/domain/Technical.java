package com.thoughtworks.jigsaw.domain;

import lombok.Getter;
import lombok.Setter;

public class Technical {
    @Getter @Setter private String name;
    @Getter @Setter private String category;

    public Technical(String name, String category) {
        this.name = name;
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Technical)) return false;

        Technical technical = (Technical) o;

        if (name != null ? !name.equals(technical.name) : technical.name != null) return false;
        return !(category != null ? !category.equals(technical.category) : technical.category != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }
}
