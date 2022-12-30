package com.rest.library.kodillalibrary.repository;

import com.rest.library.kodillalibrary.domain.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface BookDao extends CrudRepository<Book, Long> {


   @Override
   Book save(Book book);

   List<Book> findByTitleEqualsIgnoreCaseAndAuthorEqualsIgnoreCase(String title, String author);

   List<Book> findByTitleEqualsIgnoreCase(String title);

}
