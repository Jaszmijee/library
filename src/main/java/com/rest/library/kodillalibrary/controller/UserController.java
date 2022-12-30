package com.rest.library.kodillalibrary.controller;

import com.rest.library.kodillalibrary.domain.User;
import com.rest.library.kodillalibrary.dto.UserDto;
import com.rest.library.kodillalibrary.exceptions.UserExistsException;
import com.rest.library.kodillalibrary.exceptions.UserNotFoundException;
import com.rest.library.kodillalibrary.mapper.UserMapper;
import com.rest.library.kodillalibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library/user")
public class UserController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @PostMapping()
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto)
            throws UserExistsException {

        if (userService.findIfUserExists(userDto).isEmpty()) {
            User user = userMapper.mapUserDtoToUser(userDto);
            userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            throw new UserExistsException();
        }
    }
}
