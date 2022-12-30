package com.rest.library.kodillalibrary.service;

import com.rest.library.kodillalibrary.controller.UserController;
import com.rest.library.kodillalibrary.domain.User;
import com.rest.library.kodillalibrary.dto.UserDto;
import com.rest.library.kodillalibrary.exceptions.UserExistsException;
import com.rest.library.kodillalibrary.repository.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserControllerTestSuite {

    @Autowired
    private UserController userController;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Test
    public void testCreateUserOK() throws UserExistsException {
        //Given
        UserDto userDto = new UserDto("Bilbo", "Baggins");

        // When
        ResponseEntity<Void> createNewUser = userController.createUser(userDto);
        long usersNumber = userDao.count();

        assertEquals(1, usersNumber);

        //Then
        assertThat(createNewUser).isNotNull();
        assertThat(createNewUser.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertEquals(1, usersNumber);

        //CleanUp
        List<User> usersList = userService.findIfUserExists(userDto);
        long userID = usersList.get(0).getId();
        userDao.deleteById(userID);
    }


    @Test
    public void testCreateUserAlreadyExistsException() throws UserExistsException {
        //Given
        UserDto userDto = new UserDto("Bilbo", "Baggins");
        UserDto userDto1 = new UserDto("Bilbo", "Baggins");

        //When
        userController.createUser(userDto);

        // Then
        assertThrows(UserExistsException.class, () -> userController.createUser(userDto1));

        //CleanUp
        List<User> usersList = userService.findIfUserExists(userDto);
        long userID = usersList.get(0).getId();
        userDao.deleteById(userID);
    }
}



