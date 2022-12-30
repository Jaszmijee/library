package com.rest.library.kodillalibrary.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "RENTALS")
public class Rental {

    private long id;
    private User user;
    private LocalDate rentalDate;
    private List<Copy> copies = new ArrayList<>();

    private LocalDate returnDate;

    public Rental(User user, LocalDate rentalDate, LocalDate returnDate, List<Copy> copies) {
        this.user = user;
        this.rentalDate = rentalDate;
        this.copies = copies;
        this.returnDate = returnDate;
    }

    public Rental(LocalDate rentalDate, LocalDate returnDate) {
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

    public Rental() {
    }

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "RENT_ID", unique = true)
    public long getId() {
        return id;
    }

    @NotNull
    @ManyToOne
    @JoinColumn(name = "USERS_USER_ID")
    public User getUser() {
        return user;
    }

    @NotNull
    @Column(name = "RENTAL_DATE")
    public LocalDate getRentalDate() {
        return rentalDate;
    }


    @Column(name = "RETURN_DATE")
    public LocalDate getReturnDate() {
        return returnDate;
    }

    @OneToMany(fetch = FetchType.EAGER)
    public List<Copy> getCopies() {
        return copies;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public void setCopies(List<Copy> copies) {
        this.copies = copies;
    }
}

