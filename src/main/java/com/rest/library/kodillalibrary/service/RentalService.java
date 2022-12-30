package com.rest.library.kodillalibrary.service;

import com.rest.library.kodillalibrary.domain.Copy_Status;
import com.rest.library.kodillalibrary.exceptions.RentalNotFoundException;
import com.rest.library.kodillalibrary.domain.Rental;
import com.rest.library.kodillalibrary.repository.RentalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalService {

    @Autowired
    RentalDao rentalDao;

    public Rental createRentalRequest(final Rental rental) {
        return rentalDao.save(rental);
    }

     public Rental findRentalById(final Long rentalID) throws RentalNotFoundException{
         return rentalDao.findById(rentalID).orElseThrow(RentalNotFoundException::new);
        }

     public List<String> findTitlesRented(Long rentalId) throws RentalNotFoundException {
           Rental rentalToReturn = findRentalById(rentalId);
           return rentalToReturn.getCopies().stream()
                   .filter(c -> c.getStatus().equals(Copy_Status.RENTED) || c.getStatus().equals(Copy_Status.LOST))
                    .map(copy -> copy.getBook().getTitle())
                    .collect(Collectors.toList());
  }

    public Rental saveRentalRequest(final Rental rental) {
        return rentalDao.save(rental);
    }
    }



