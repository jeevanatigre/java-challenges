package com.indpro.service;

import com.indpro.entity.Book;
import com.indpro.entity.BookBorrowDetails;

import java.util.HashMap;

public interface BookBorrowService {

    public HashMap<Long, String> save(BookBorrowDetails bookBorrowDetails);

    public BookBorrowDetails returnBook(BookBorrowDetails bookBorrowDetails);

}
