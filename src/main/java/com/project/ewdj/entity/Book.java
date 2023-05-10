package com.project.ewdj.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.project.ewdj.util.MaxSizeArrayList;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Data;

@Entity
@Data
@NoArgsConstructor
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    public String bookName;
    @Column(name = "isbn", nullable = false)
    private String isbn_nummer;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Column(name = "rating", nullable = false)
    private float rating;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "books_authors", joinColumns = {
            @JoinColumn(name = "book_id", referencedColumnName = "id") }, inverseJoinColumns = {
                    @JoinColumn(name = "author_id", referencedColumnName = "id") })
    private List<Author> authors = new MaxSizeArrayList<>(3);

    @OneToMany(mappedBy = "book")
    private List<Location> locations = new MaxSizeArrayList<>(3);

    public Book(String bookName, String isbn_nummer, BigDecimal price) {
        this.bookName = bookName;
        this.isbn_nummer = isbn_nummer;
        this.price = price;
        // this.authors = authors;
    }
}
