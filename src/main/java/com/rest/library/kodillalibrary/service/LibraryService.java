package com.rest.library.kodillalibrary.service;

import com.rest.library.kodillalibrary.dto.BookDto;
import com.rest.library.kodillalibrary.exceptions.CopyNotFoundException;
import com.rest.library.kodillalibrary.domain.*;
import com.rest.library.kodillalibrary.repository.BookDao;
import com.rest.library.kodillalibrary.repository.CopyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class LibraryService {

    @Autowired
    BookDao bookDao;
    @Autowired
    CopyDao copyDao;

    public List<Book> findIfBookExists(BookDto bookDto) {
        return bookDao.findByTitleEqualsIgnoreCaseAndAuthorEqualsIgnoreCase(bookDto.getTitle(), bookDto.getAuthor());
    }

    public List<Book> findIfBookExistsByTitle(String title) {
        return bookDao.findByTitleEqualsIgnoreCase(title);
    }


    public List<Copy> findCopiesAvailableByTitle(String title) {
        return copyDao.findCopiesAvailableByTitle(title);
    }

    public Book addBook(final Book book) {
        return bookDao.save(book);
    }

    public Copy saveCopy(final Copy copy) {
        return copyDao.save(copy);
    }

    public Copy getCopyById(final Long id) throws CopyNotFoundException {
        return copyDao.findByID(id).orElseThrow(CopyNotFoundException::new);
    }

    public Book findBookByCopyId(Long copyId) throws CopyNotFoundException {
        return getCopyById(copyId).getBook();
    }
}
