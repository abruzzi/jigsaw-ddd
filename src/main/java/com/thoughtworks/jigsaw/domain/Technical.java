package com.thoughtworks.jigsaw.domain;

import javax.persistence.*;

@Entity
@Table(name = "technicals")
public class Technical {
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    private String name;
    private String category;

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

    public Technical() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
