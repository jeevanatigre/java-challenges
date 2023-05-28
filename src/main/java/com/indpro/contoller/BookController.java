package com.indpro.contoller;

import com.indpro.dto.UserRequest;
import com.indpro.entity.Book;
import com.indpro.entity.BookBorrowDetails;
import com.indpro.entity.User;
import com.indpro.service.BookBorrowService;
import com.indpro.service.BookService;
import com.indpro.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookBorrowService bookBorrowService;

    @PostMapping("/book")
    public ResponseEntity<?> saveBook(@RequestBody @Valid Book book) {
            return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
    }

    @PutMapping("/book")
    public ResponseEntity<?> editBook(@RequestBody @Valid Book book) {
        return new ResponseEntity<>(bookService.save(book), HttpStatus.OK);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<?> getBook(@PathVariable("id") Long id) {
        return new ResponseEntity<>(bookService.findBookById(id), HttpStatus.OK);
    }

    @GetMapping("/book")
    public ResponseEntity<?> searchBook(@RequestParam("searchString") String searchString) {
        return new ResponseEntity<>(bookService.findByBookNameOrCatagoryOrAuthorOrIsbnOrCompany(searchString), HttpStatus.OK);
    }

    @PostMapping("/book/borrow")
    public ResponseEntity<?> borrowBook(@RequestBody BookBorrowDetails bookBorrowDetails) {
        return new ResponseEntity<>(bookBorrowService.save(bookBorrowDetails).get(bookBorrowDetails.getUserId()), HttpStatus.OK);
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id) {
        if(bookService.deleteById(id))
            return ResponseEntity.ok("Entity deleted");
        else
            return new ResponseEntity<>("This book is already borrowed and hence can not be deleted", HttpStatus.FORBIDDEN);
    }

    @PostMapping("/book/return")
    public ResponseEntity<?> returnBook(@RequestBody BookBorrowDetails bookBorrowDetails) {
        return new ResponseEntity<>(bookBorrowService.returnBook(bookBorrowDetails), HttpStatus.OK);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

}
