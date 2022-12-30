package com.rest.library.kodillalibrary.repository;

import com.rest.library.kodillalibrary.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserDaoTestSuite {

    @Autowired
    private UserDao userDao;

    @Test
    public void testAddUser() {
        // Given
        User user = new User("Bilbo", "Baggins", LocalDate.of(2009, 12, 05));
        userDao.save(user);

        // When
        long usersNumber = userDao.count();

        //Then
        assertEquals(1, usersNumber);

        //CleanUp
        long userID = user.getId();
        userDao.deleteById(userID);
    }
}
