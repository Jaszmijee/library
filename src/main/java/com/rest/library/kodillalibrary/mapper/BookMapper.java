package com.rest.library.kodillalibrary.mapper;

import com.rest.library.kodillalibrary.domain.Book;
import com.rest.library.kodillalibrary.dto.BookDto;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {

    public Book mapBookDtoToBook(BookDto bookDto) {
        return new Book(
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getPublicationYear()
        );
    }
}


