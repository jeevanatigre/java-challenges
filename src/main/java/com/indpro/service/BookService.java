package com.indpro.service;

import com.indpro.dto.UserRequest;
import com.indpro.entity.Book;
import com.indpro.entity.User;

import java.util.List;
import java.util.Optional;

public interface BookService {

    public Book save(Book book);

    public List<Book> findByBookNameOrCatagoryOrAuthorOrIsbnOrCompany(String bookSearch);

    public Book findBookById(Long id);

    public Book editBook(Book book);

    public Boolean deleteById(Long id);
}
