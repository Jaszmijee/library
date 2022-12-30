package com.rest.library.kodillalibrary.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "COPIES")
public class Copy {

    private long ID;
    private Book book;
    private Copy_Status status;

    private Rental rental;

    public Copy(Book book, Copy_Status status) {
        this.book = book;
        this.status = status;
    }

    public Copy() {
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "COPY_ID", unique = true)
    public long getID() {
        return ID;
    }

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    public Book getBook() {
        return book;
    }

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "COPY_STATUS")
    public Copy_Status getStatus() {
        return status;
    }

    @ManyToOne
    @JoinColumn(name = "RENTAL_ID")
    public Rental getRental() {
        return rental;
    }

    private void setID(long ID) {
        this.ID = ID;
    }

    private void setBook(Book book) {
        this.book = book;
    }

    public void setStatus(Copy_Status status) {
        this.status = status;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }
}
