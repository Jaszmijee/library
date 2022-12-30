package com.rest.library.kodillalibrary.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "USERS")
public class User {

    private long id;
    private String firstName;
    private String lastName;
    private LocalDate created;

    private List<Rental> rentals = new ArrayList<>();

    public User(String firstName, String lastName, LocalDate created) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.created = created;
    }

    public User() {
    }

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "USER_ID", unique = true)
    public long getId() {
        return id;
    }

    @NotNull
    @Column(name = "USER_FIRSTNAME")
    public String getFirstName() {
        return firstName;
    }

    @NotNull
    @Column(name = "USER_LASTNAME")
    public String getLastName() {
        return lastName;
    }

    @NotNull
    @Column(name = "ACCOUNT_CREATED")
    public LocalDate getCreated() {
        return created;
    }

    @OneToMany(targetEntity = Rental.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    public List<Rental> getRentals() {
        return rentals;
    }

    private void setCreated(LocalDate created) {
        this.created = created;
    }

    private void setId(long id) {
        this.id = id;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;

        if (!Objects.equals(firstName, user.firstName)) return false;
        return Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
