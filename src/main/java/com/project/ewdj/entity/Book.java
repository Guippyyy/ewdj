package com.project.ewdj.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String bookName;

    @Column(name = "isbn", nullable = false)
    private String isbn_nummer;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "rating", nullable = false)
    private float rating;

    @OneToMany(mappedBy = "book")
    private List<BookAuthor> bookAuthors;

    @OneToMany(mappedBy = "book")
    private List<Location> locations;

    public Book(String bookName, String isbn_nummer, BigDecimal price) {
        this.bookName = bookName;
        this.isbn_nummer = isbn_nummer;
        this.price = price;
        // this.star = star;
    }

    protected Book() {
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getIsbn_nummer() {
        return isbn_nummer;
    }

    public void setIsbn_nummer(String isbn_nummer) {
        this.isbn_nummer = isbn_nummer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<BookAuthor> getBookAuthors() {
        return bookAuthors;
    }

    public void setBookAuthors(List<BookAuthor> bookAuthors) {
        this.bookAuthors = bookAuthors;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public void addBookAuthor(BookAuthor bookAuthor) {
        this.bookAuthors.add(bookAuthor);
        bookAuthor.setBook(this);
    }

    public void removeBookAuthor(BookAuthor bookAuthor) {
        this.bookAuthors.remove(bookAuthor);
        bookAuthor.setBook(null);
    }

    public void addLocation(Location location) {
        this.locations.add(location);
        location.setBook(this);
    }

    public void removeLocation(Location location) {
        this.locations.remove(location);
        location.setBook(null);
    }

}
