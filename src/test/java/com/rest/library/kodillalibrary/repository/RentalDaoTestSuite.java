package com.rest.library.kodillalibrary.repository;

import com.rest.library.kodillalibrary.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class RentalDaoTestSuite {

    @Autowired
    RentalDao rentalDao;

    @Autowired
    UserDao userDao;

    @Autowired
    CopyDao copyDao;

    @Autowired
    BookDao bookDao;

    private Copy_Status AVAILABLE = Copy_Status.AVAILABLE;

    @Test
    public void test() {
        //Given
        User user = new User("Bilbo", "Baggins", LocalDate.of(2009, 12, 05));
        userDao.save(user);

        Book buszujacy = new Book("The Catcher in the Rye", "J.D. Salinger", 1951);
        Copy copy = new Copy(buszujacy, AVAILABLE);
        Copy copy1 = new Copy(buszujacy, AVAILABLE);
        Copy copy2 = new Copy(buszujacy, AVAILABLE);

        bookDao.save(buszujacy);

        copyDao.save(copy);
        copyDao.save(copy1);
        copyDao.save(copy2);

        Rental rental = new Rental(LocalDate.of(2022, 10, 15), LocalDate.now());
        rental.setUser(user);
        rental.getCopies().add(copy1);
        rental.getCopies().add(copy2);

        rentalDao.save(rental);

        copy1.setRental(rental);
        copy2.setRental(rental);

        user.getRentals().add(rental);
        userDao.save(user);

        copyDao.save(copy);
        copyDao.save(copy1);
        copyDao.save(copy2);

        //When
        long rentalId = rental.getId();
        Optional<Rental> rentedCopies = rentalDao.findById(rentalId);

        //Then
        assertTrue(rentedCopies.isPresent());
        assertEquals(2,  rentedCopies.get().getCopies().size());

        //CleanUp
        long copyId = copy.getID();
        long copyId1 = copy1.getID();
        long copyId2 = copy2.getID();

        long bookID = buszujacy.getID();

        long userID = user.getId();

        copy1.setRental(null);
        copy2.setRental(null);

        copyDao.save(copy1);
        copyDao.save(copy2);

        userDao.deleteById(userID);

        copyDao.deleteById(copyId);
        copyDao.deleteById(copyId1);
        copyDao.deleteById(copyId2);

        bookDao.deleteById(bookID);
    }
}



