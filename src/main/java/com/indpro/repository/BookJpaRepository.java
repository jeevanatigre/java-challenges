package com.indpro.repository;

import com.indpro.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookJpaRepository extends JpaRepository<Book, Long> {

    public List<Book> findByNameOrCategoryOrAuthorOrIsbnOrCompany(String bookName,String bookCategory,String bookAuthor,String bookIsbn,String bookCompany);
}
