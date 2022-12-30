package com.rest.library.kodillalibrary.controller;

import com.rest.library.kodillalibrary.domain.*;
import com.rest.library.kodillalibrary.dto.BookDto;
import com.rest.library.kodillalibrary.dto.CopyDto;
import com.rest.library.kodillalibrary.exceptions.CopyNotFoundException;
import com.rest.library.kodillalibrary.mapper.BookMapper;
import com.rest.library.kodillalibrary.mapper.CopyMapper;
import com.rest.library.kodillalibrary.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library/management")
public class LibraryController {

    @Autowired
    LibraryService libraryService;

    @Autowired
    BookMapper bookMapper;

    @Autowired
    CopyMapper copyMapper;


    @GetMapping()
    public ResponseEntity<CopyDto> findAvailableCopies(@RequestParam String title)
            throws CopyNotFoundException {

        if (libraryService.findIfBookExistsByTitle(title).isEmpty()) {
            throw new CopyNotFoundException();
        } else {
            List<Copy> listOfAvailable = libraryService.findCopiesAvailableByTitle(title);
            return ResponseEntity.ok(copyMapper.mapCopyToCopyDto(listOfAvailable));
        }
    }

    @PostMapping
    public ResponseEntity<Void> addBookAndCopy(@RequestBody BookDto bookDto) {

        Book book = bookMapper.mapBookDtoToBook(bookDto);
        Copy copy = new Copy(book, Copy_Status.AVAILABLE);
        if (libraryService.findIfBookExists(bookDto).isEmpty()) {
            libraryService.addBook(book);
            libraryService.saveCopy(copy);
        } else {
            book = libraryService.findIfBookExists(bookDto).get(0);
            copy = new Copy(book, Copy_Status.AVAILABLE);
            libraryService.saveCopy(copy);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> updateCopyStatus(@RequestParam Long copyId, @RequestParam Copy_Status copy_status)
            throws CopyNotFoundException {

        Copy copytoUpdae = libraryService.getCopyById(copyId);
        copytoUpdae.setStatus(copy_status);
        libraryService.saveCopy(copytoUpdae);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
