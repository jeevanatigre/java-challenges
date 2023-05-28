package com.indpro.serviceImpl;

import com.indpro.entity.Book;
import com.indpro.entity.BookBorrowDetails;
import com.indpro.repository.BookBorrowRepository;
import com.indpro.repository.BookJpaRepository;
import com.indpro.service.BookBorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class BookBorrowServiceImpl implements BookBorrowService {

    @Autowired
    private BookBorrowRepository bookBorrowRepository;

    @Autowired
    private BookJpaRepository bookJpaRepository;

    @Override
    public HashMap<Long, String> save(BookBorrowDetails bookBorrowDetails) {
        HashMap<Long, String> bookBorrowHashMap = new HashMap<Long, String>();
        if(bookBorrowRepository.findByUserId(bookBorrowDetails.getUserId()) != null && bookBorrowRepository.findByUserId(bookBorrowDetails.getUserId()).getBookBorrowFine() > 0) {
            bookBorrowHashMap.put(bookBorrowDetails.getUserId(), "User can not borrow for " + bookBorrowRepository.findByUserId(bookBorrowDetails.getUserId()).getBookBorrowFine() + " more days");
            return bookBorrowHashMap;
        } else {
            bookBorrowDetails = bookBorrowRepository.save(bookBorrowDetails);
            if (bookBorrowDetails != null) {
                Book book = bookJpaRepository.findById(bookBorrowDetails.getBookId()).get();
                book.setIsBorrowed(true);
                bookJpaRepository.save(book);
            }
            bookBorrowDetails.setBookBorrowFine(0);
            bookBorrowHashMap.put(bookBorrowDetails.getUserId(), "User borrowed book for " + (int) TimeUnit.DAYS.convert(bookBorrowDetails.getFromBorrowed().getTime() - bookBorrowDetails.getToBorrowed().getTime(), TimeUnit.MILLISECONDS) + " days");
            return bookBorrowHashMap;
        }
    }

    @Override
    public BookBorrowDetails returnBook(BookBorrowDetails bookBorrowDetails) {
        bookBorrowDetails.setIsReturned(true);
        if (new Date().after(bookBorrowDetails.getToBorrowed())) {
            bookBorrowDetails.setBookBorrowFine((int) TimeUnit.DAYS.convert(new Date().getTime() - bookBorrowDetails.getToBorrowed().getTime(), TimeUnit.MILLISECONDS));
        }
        if (bookBorrowDetails != null) {
            Book book = bookJpaRepository.findById(bookBorrowDetails.getBookId()).get();
            book.setIsBorrowed(false);
            bookJpaRepository.save(book);
        }
        return bookBorrowDetails;
    }
}
