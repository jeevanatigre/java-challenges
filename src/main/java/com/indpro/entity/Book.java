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
@Table(name = "Books")
public class Book implements Serializable {

    private static final long serialVersionUID = 42L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CATAGORY")
    private String category;

    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "COMPANY")
    private String company;

    @Column(name = "IS_BORROWED")
    private Boolean isBorrowed;

}
