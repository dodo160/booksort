package com.booksort.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.Objects;

@XmlRootElement(name = "Autor")
@XmlAccessorType(XmlAccessType.FIELD)
public class Author implements Serializable {

    @XmlAttribute(name = "Jmeno", required = true)
    private String name;

    @XmlAttribute(name = "Prijmeni", required = true)
    private String surename;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getAuthor(){
        return name + " " + surename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(name, author.name) && Objects.equals(surename, author.surename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surename);
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", surename='" + surename + '\'' +
                '}';
    }
}
