package com.project.ewdj.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "favorite_books")
@Getter
@Setter
public class FavoriteBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String bookName;

    @Column(name = "isbn", nullable = false)
    private String isbn_nummer;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "author_1_id")
    private Author author1;

    @ManyToOne
    @JoinColumn(name = "author_2_id")
    private Author author2;

    @ManyToOne
    @JoinColumn(name = "author_3_id")
    private Author author3;

    public FavoriteBook(Long id, String bookName, Author author1, BigDecimal price, String isbn_nummer) {
        this.id = id;
        this.bookName = bookName;
        this.author1 = author1;
        this.price = price;
        this.isbn_nummer = isbn_nummer;
    }

    public FavoriteBook(Long id, String bookName, Author author1, Author author2, BigDecimal price,
            String isbn_nummer) {
        this.id = id;
        this.bookName = bookName;
        this.author1 = author1;
        this.author2 = author2;
        this.price = price;
        this.isbn_nummer = isbn_nummer;
    }

    public FavoriteBook(Long id, String bookName, Author author1, Author author2, Author author3, BigDecimal price,
            String isbn_nummer) {
        this.id = id;
        this.bookName = bookName;
        this.author1 = author1;
        this.author2 = author2;
        this.author3 = author3;
        this.price = price;
        this.isbn_nummer = isbn_nummer;
    }

    protected FavoriteBook() {
    }
}