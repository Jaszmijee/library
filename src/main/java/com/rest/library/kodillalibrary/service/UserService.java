package com.rest.library.kodillalibrary.service;

import com.rest.library.kodillalibrary.exceptions.UserNotFoundException;
import com.rest.library.kodillalibrary.domain.User;
import com.rest.library.kodillalibrary.dto.UserDto;
import com.rest.library.kodillalibrary.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User createUser(final User user) {
        return userDao.save(user);
    }

    public List<User> findIfUserExists(UserDto userDto) {
        return userDao.findByFirstNameEqualsIgnoreCaseAndLastNameEqualsIgnoreCase(userDto.getFirstName(), userDto.getLastName());
    }

    public User findUserById(Long userId) throws UserNotFoundException {
        return userDao.findById(userId).orElseThrow(UserNotFoundException::new);
    }
}
