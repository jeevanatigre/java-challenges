package com.indpro.serviceImpl;

import com.indpro.entity.Book;
import com.indpro.repository.BookJpaRepository;
import com.indpro.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookJpaRepository bookJpaRepository;

    @Override
    public Book save(Book book) {
        return bookJpaRepository.save(book);
    }

    @Override
    public List<Book> findByBookNameOrCatagoryOrAuthorOrIsbnOrCompany(String bookSearch) {
        return bookJpaRepository.findByNameOrCategoryOrAuthorOrIsbnOrCompany(bookSearch, bookSearch, bookSearch, bookSearch, bookSearch);
    }

    @Override
    public Book findBookById(Long id) {
        return bookJpaRepository.findById(id).get();
    }

    @Override
    public Book editBook(Book book) {
        return bookJpaRepository.save(book);
    }

    @Override
    public Boolean deleteById(Long id) {
        if(!bookJpaRepository.findById(id).get().getIsBorrowed()) {
            bookJpaRepository.deleteById(id);
            return true;
        } else
            return false;
    }

}
