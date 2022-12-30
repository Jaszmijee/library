package com.rest.library.kodillalibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CopyDto {
    private String bookTitle;
    private String author;
    private int copies;
}
