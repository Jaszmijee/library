package com.rest.library.kodillalibrary.dto;

import lombok.Getter;
import lombok.AllArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
public class RentDtoRequest {
    private Long userId;
    private List<String> titles;
}
