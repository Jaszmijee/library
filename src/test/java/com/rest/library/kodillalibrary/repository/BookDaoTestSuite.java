package com.rest.library.kodillalibrary.repository;

import com.rest.library.kodillalibrary.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BookDaoTestSuite {

    @Autowired
    private BookDao bookDao;

    Book buszujacy = new Book("The Catcher in the Rye", "J.D. Salinger", 1951);

    @Test
    public void testAddBook() {

        // When
        bookDao.save(buszujacy);

        long booksNumber = bookDao.count();

        //Then
        assertEquals(1, booksNumber);

        //CleanUp
        long bookID = buszujacy.getID();
        bookDao.deleteById(bookID);
    }
}
