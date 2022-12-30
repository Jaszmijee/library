package com.rest.library.kodillalibrary.repository;

import com.rest.library.kodillalibrary.domain.Rental;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Transactional
@Repository
public interface RentalDao extends CrudRepository<Rental, Long> {

    @Override
    Rental save(Rental rental);

    @Override
    Optional<Rental> findById(Long rentalId);


    }
