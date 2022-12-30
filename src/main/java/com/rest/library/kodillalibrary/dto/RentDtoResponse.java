package com.rest.library.kodillalibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class RentDtoResponse {
    private Long rentalId;
    private Long userId;
    private LocalDate created;
    private List<String> booksRented;
    private List<String> booksNotRented;
    }
