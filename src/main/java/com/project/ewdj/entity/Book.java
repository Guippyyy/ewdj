package com.project.ewdj.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String bookName;
    private double price;
    private long isbn_nummer;
    // private int star;
    private String autor;

    public Book(String bookName, long isbn_nummer, double price, String autor) {
        this.bookName = bookName;
        this.isbn_nummer = isbn_nummer;
        this.price = price;
        // this.star = star;
        this.autor = autor;
    }

    protected Book() {
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getIsbn_nummer() {
        return isbn_nummer;
    }

    public void setIsbn_nummer(long isbn_nummer) {
        this.isbn_nummer = isbn_nummer;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
