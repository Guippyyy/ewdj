package com.project.ewdj.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "book")
@Getter
@Setter
public class Book {

    @Id
    private int id;
    private String bookName;
    private double price;
    private long isbn_nummer;
    private int star;
    private String autor;

    public Book(int id, String bookName, long isbn_nummer, double price, String autor) {
        this.id = id;
        this.bookName = bookName;
        this.isbn_nummer = isbn_nummer;
        this.price = price;
        // this.star = star;
        this.autor = autor;
    }

    protected Book() {
    }

}
