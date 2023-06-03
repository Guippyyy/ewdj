package com.project.ewdj.util;

import com.project.ewdj.entity.Book;
import lombok.Getter;

@Getter
public class HomeListItem {
    public HomeListItem(Book book, boolean isFavorite) {
        this.book = book;
        this.isFavorite = isFavorite;
    }

    public Book book;
    public boolean isFavorite;
}