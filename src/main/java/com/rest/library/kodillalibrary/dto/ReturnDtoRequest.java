package com.rest.library.kodillalibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReturnDtoRequest {
    Long rentalId;
    String bookTitle;
    String bookStatus;
    boolean paid;
}
