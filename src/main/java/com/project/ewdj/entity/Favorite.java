package com.project.ewdj.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "favorite")
@Getter
@Setter
public class Favorite {

    @Id
    private int id;
    private String bookName;
    private String autor;
    private double price;
    private long isbn_nummer;

    public Favorite(int id, String bookName, String autor, double price, long isbn_nummer) {
        this.id = id;
        this.bookName = bookName;
        this.autor = autor;
        this.price = price;
        this.isbn_nummer = isbn_nummer;
    }

    protected Favorite() {
    }
}