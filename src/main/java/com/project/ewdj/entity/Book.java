package com.project.ewdj.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.ewdj.util.MaxSizeArrayList;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Data
@NoArgsConstructor
@Table(name = "books")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    public String bookName;
    @Column(name = "isbn", nullable = false)
    private String isbnCode;
    @Column(name = "price", nullable = true)
    private BigDecimal price;
    @Column(name = "rating", nullable = false)
    private float rating;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "books_authors", joinColumns = {
            @JoinColumn(name = "book_id", referencedColumnName = "id") }, inverseJoinColumns = {
                    @JoinColumn(name = "author_id", referencedColumnName = "id") })
    @JsonManagedReference
    private List<Author> authors = new MaxSizeArrayList<>(3);

    @OneToMany(mappedBy = "book")
    @JsonManagedReference
    private List<Location> locations = new MaxSizeArrayList<>(3);

    public Book(String bookName, String isbnCode, BigDecimal price) {
        this.bookName = bookName;
        this.isbnCode = isbnCode;
        this.price = price;
    }
}
