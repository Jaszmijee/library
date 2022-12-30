package com.rest.library.kodillalibrary.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "BOOKS")
/*,uniqueConstraints = {@UniqueConstraint(columnNames = {"BOOK_TITLE", "BOOK_AUTHOR", "BOOK_PUBLICATION_DATE"}
)})*/

public class Book {

    private long ID;
    private String title;
    private String author;
    private int publicationYear;

    private List<Copy> copies = new ArrayList<>();

    public Book(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public Book() {
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "BOOK_ID", unique = true)
    public long getID() {
        return ID;
    }

    @NotNull
    @Column(name = "BOOK_TITLE")
    public String getTitle() {
        return title;
    }

    @NotNull
    @Column(name = "BOOK_AUTHOR")
    public String getAuthor() {
        return author;
    }

    @NotNull
    @Column(name = "BOOK_PUBLICATION_DATE")
    public int getPublicationYear() {
        return publicationYear;
    }

    @OneToMany(targetEntity = Copy.class,
            mappedBy = "book",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    public List<Copy> getCopies() {
        return copies;
    }

    private void setID(long ID) {
        this.ID = ID;
    }

    private void setPublicationYear(int publicationDate) {
        this.publicationYear = publicationDate;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private void setAuthor(String author) {
        this.author = author;
    }

    private void setCopies(List<Copy> copies) {
        this.copies = copies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;

        if (publicationYear != book.publicationYear) return false;
        if (!Objects.equals(title, book.title)) return false;
        return Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + publicationYear;
        return result;
    }
}


