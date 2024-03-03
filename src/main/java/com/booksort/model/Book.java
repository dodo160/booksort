package com.booksort.model;

import jakarta.xml.bind.annotation.*;

import java.io.Serializable;
import java.util.Objects;

@XmlRootElement(name = "Kniha")
@XmlAccessorType(XmlAccessType.FIELD)
public class Book implements Serializable {

    @XmlAttribute(name = "ISBN", required = true)
    private String isbn;

    @XmlAttribute(name = "Vydano", required = true)
    private int release;

    @XmlElement(name = "Nazev", required = true)
    private String name;

    @XmlElement(name = "Autor", required = true)
    private Author author;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getRelease() {
        return release;
    }

    public void setRelease(int release) {
        this.release = release;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return release == book.release && Objects.equals(isbn, book.isbn) && Objects.equals(name, book.name) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, release, name, author);
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", release=" + release +
                ", name='" + name + '\'' +
                ", author=" + author +
                '}';
    }
}
