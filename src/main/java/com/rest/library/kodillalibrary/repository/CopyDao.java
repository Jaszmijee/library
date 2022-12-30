package com.rest.library.kodillalibrary.repository;

import com.rest.library.kodillalibrary.domain.Copy;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface CopyDao extends CrudRepository<Copy, Long> {

    @Override
    Copy save(Copy copy);

    Optional<Copy> findByID(Long id);

    @Query(value = "SELECT C.* FROM COPIES C " +
            "JOIN BOOKS B ON B.BOOK_ID = C.BOOK_ID " +
            "WHERE B.BOOK_TITLE = :TITLE AND C.COPY_STATUS = 'AVAILABLE'", nativeQuery = true)
    List<Copy> findCopiesAvailableByTitle(@Param("TITLE") String title);
}
