package com.project.ewdj.entity;

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

@Getter
@Setter
@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "place_code_1", nullable = false)
    private String placeCode1;

    @Column(name = "place_code_2", nullable = false)
    private String placeCode2;

    @Column(name = "place_name", nullable = false)
    private String placeName;

    public Location(String placeCode1, String placeCode2, String placeName) {
        this.placeCode1 = placeCode1;
        this.placeCode2 = placeCode2;
        this.placeName = placeName;
    }

    protected Location() {
    }

}
