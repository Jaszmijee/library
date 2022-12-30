package com.rest.library.kodillalibrary.mapper;

import com.rest.library.kodillalibrary.dto.RentDtoResponse;
import com.rest.library.kodillalibrary.domain.Rental;
import com.rest.library.kodillalibrary.dto.RentedTitlesDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RentMapper {

    public RentDtoResponse mapRentalToRentDtoResponse(Rental rental, List<String> rented, List<String> unavailable) {
        return new RentDtoResponse(
                rental.getId(),
                rental.getUser().getId(),
                rental.getRentalDate(),
                rented,
                unavailable
        );


    }

    public List<RentedTitlesDto> mapRentedTitlesToRentedTitlesDto(List<String> rentedTitles) {
        List<RentedTitlesDto> list = new ArrayList<>();
        for (String title : rentedTitles) {
            list.add(new RentedTitlesDto(title));
        }
        return list;
    }
}
