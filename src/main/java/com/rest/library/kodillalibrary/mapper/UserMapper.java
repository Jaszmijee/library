package com.rest.library.kodillalibrary.mapper;

import com.rest.library.kodillalibrary.domain.User;
import com.rest.library.kodillalibrary.dto.UserDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserMapper {

public User mapUserDtoToUser(UserDto userDto){
return new User(
        userDto.getFirstName(),
        userDto.getLastName(),
        LocalDate.now()
);
}
}
