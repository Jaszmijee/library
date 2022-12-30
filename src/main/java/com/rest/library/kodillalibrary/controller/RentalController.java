package com.rest.library.kodillalibrary.controller;

import com.rest.library.kodillalibrary.domain.*;
import com.rest.library.kodillalibrary.dto.RentDtoRequest;
import com.rest.library.kodillalibrary.dto.RentDtoResponse;
import com.rest.library.kodillalibrary.dto.RentedTitlesDto;
import com.rest.library.kodillalibrary.dto.ReturnDtoRequest;
import com.rest.library.kodillalibrary.exceptions.*;
import com.rest.library.kodillalibrary.mapper.RentMapper;
import com.rest.library.kodillalibrary.service.LibraryService;
import com.rest.library.kodillalibrary.service.RentalService;
import com.rest.library.kodillalibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/library/rental")
public class RentalController {

    @Autowired
    UserService userService;

    @Autowired
    RentalService rentalService;

    @Autowired
    RentMapper rentMapper;

    @Autowired
    LibraryService libraryService;

    @Autowired
    LibraryController libraryController;


    @GetMapping(value = "{rentalId}")
    public ResponseEntity<List<RentedTitlesDto>> findTitlesRented(@PathVariable Long rentalId)
            throws RentalNotFoundException {

        List<String> rentedTitlesList = rentalService.findTitlesRented(rentalId);
        return ResponseEntity.ok(rentMapper.mapRentedTitlesToRentedTitlesDto(rentedTitlesList));
    }

    @PostMapping
    public ResponseEntity<RentDtoResponse> rentBook(@RequestBody RentDtoRequest rentDtoRequest)
            throws UserNotFoundException, CopyNotFoundException, CopyNotAvailableException {

        User user = userService.findUserById(rentDtoRequest.getUserId());
        List<Copy> availableList = rentDtoRequest.getTitles().stream()
                .map(libraryService::findCopiesAvailableByTitle)
                .filter(list -> !list.isEmpty())
                .map(copies -> copies.get(0))
                .collect(Collectors.toList());

        for (Copy copy : availableList) {
            libraryController.updateCopyStatus(copy.getID(), Copy_Status.RENTED);
        }

        List<String> titlesAvailable = availableList.stream().map(c -> c.getBook().getTitle())
                .collect(Collectors.toList());

        if (titlesAvailable.isEmpty()) {
            throw new CopyNotAvailableException();
        }

        List<String> titlesNotAvailable = new ArrayList<>(rentDtoRequest.getTitles());
        titlesNotAvailable.removeAll(titlesAvailable);
        Rental rental = new Rental(user, LocalDate.now(), null, availableList);
        rentalService.createRentalRequest(rental);

        return new ResponseEntity(rentMapper.mapRentalToRentDtoResponse(rental, titlesAvailable, titlesNotAvailable), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> returnBook(@RequestBody ReturnDtoRequest returnDtoRequest)
            throws RentalNotFoundException, CopyNotFoundException, RentalCompletedException {

        Rental rentalToReturn = rentalService.findRentalById(returnDtoRequest.getRentalId());

        if (rentalToReturn.getReturnDate() != null) {
            throw new RentalCompletedException();
        }

        List<String> listOfTitles = rentalService.findTitlesRented(returnDtoRequest.getRentalId());

        if (!listOfTitles.contains(returnDtoRequest.getBookTitle())) {
            throw new CopyNotFoundException();
        }

        List<Copy> copyList = rentalToReturn.getCopies().stream()
                .filter(copies -> copies.getBook().getTitle().equals(returnDtoRequest.getBookTitle())).toList();

        if ((returnDtoRequest.getBookStatus().equalsIgnoreCase("OK"))
                || (returnDtoRequest.isPaid())) {

            libraryController.updateCopyStatus(copyList.get(0).getID(), Copy_Status.AVAILABLE);

        } else {
            libraryController.updateCopyStatus(copyList.get(0).getID(), Copy_Status.LOST);
        }

        if (rentalService.findTitlesRented(returnDtoRequest.getRentalId()).isEmpty()) {
            rentalToReturn.setReturnDate(LocalDate.now());
            rentalService.saveRentalRequest(rentalToReturn);
        }

        return ResponseEntity.ok().build();
    }
}
