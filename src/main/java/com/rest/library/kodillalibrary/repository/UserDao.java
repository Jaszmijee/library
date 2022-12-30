package com.rest.library.kodillalibrary.repository;

import com.rest.library.kodillalibrary.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface UserDao extends CrudRepository<User, Long> {

    @Override
    User save(User user);

    List<User> findByFirstNameEqualsIgnoreCaseAndLastNameEqualsIgnoreCase(String firstName, String lastName);

    @Override
    Optional<User> findById(Long userId);
}
