package com.project.ewdj.util;

import java.util.ArrayList;
import java.util.List;

import com.project.ewdj.entity.Book;

import lombok.Getter;

@Getter
public class HomeListItem {
    public HomeListItem(Book book, boolean isFavorite) {
        this.book = book;
        this.isFavorite = isFavorite;
    }

    // public List<HomeListItem> items = new ArrayList<>();

    public Book book;
    public boolean isFavorite;
}