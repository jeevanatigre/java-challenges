package com.indpro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Entity
@Table(name = "BookBorrowDetails")
public class BookBorrowDetails implements Serializable {

    private static final long serialVersionUID = 42L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "BOOK_ID")
    private Long bookId;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "FROM_BORROWED")
    private Date fromBorrowed;

    @Column(name = "TO_BORROWED")
    private Date toBorrowed;

    @Column(name = "IS_RETURNED")
    private Boolean isReturned;

    @Column(name = "BOOK_BORROW_FINE")
    private Integer bookBorrowFine;

}
