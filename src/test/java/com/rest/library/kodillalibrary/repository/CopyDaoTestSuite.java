package com.rest.library.kodillalibrary.repository;

import com.rest.library.kodillalibrary.domain.Book;
import com.rest.library.kodillalibrary.domain.Copy;
import com.rest.library.kodillalibrary.domain.Copy_Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CopyDaoTestSuite {

    @Autowired
    private CopyDao copyDao;

    @Autowired
    private BookDao bookDao;

    private Copy_Status AVAILABLE = Copy_Status.AVAILABLE;

    Book buszujacy = new Book("The Catcher in the Rye", "J.D. Salinger", 1951);
    Copy copy = new Copy(buszujacy, AVAILABLE);
    Copy copy1 = new Copy(buszujacy, AVAILABLE);
    Copy copy2 = new Copy(buszujacy, AVAILABLE);

    @Test
    public void testAddCopies() {
        //Given
        bookDao.save(buszujacy);

        //When
        copyDao.save(copy);
        copyDao.save(copy1);
        copyDao.save(copy2);

        long booksNumber = bookDao.count();
        long copiesNumber = copyDao.count();

        //Then
        assertEquals(1, booksNumber);
        assertEquals(3, copiesNumber);

        //CleanUp
        long copyId = copy.getID();
        long copyId1 = copy1.getID();
        long copyId2 = copy2.getID();
        copyDao.deleteById(copyId);
        copyDao.deleteById(copyId1);
        copyDao.deleteById(copyId2);

        long bookID = buszujacy.getID();
        bookDao.deleteById(bookID);
    }
}
