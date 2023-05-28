package com.indpro.repository;

import com.indpro.entity.BookBorrowDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookBorrowRepository extends JpaRepository<BookBorrowDetails, Long> {

    public BookBorrowDetails findByUserId(Long userId);

}
