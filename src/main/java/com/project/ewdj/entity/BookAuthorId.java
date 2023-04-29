// package com.project.ewdj.entity;

// import java.io.Serializable;
// import jakarta.persistence.Embeddable;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// @Getter
// @Setter
// @NoArgsConstructor
// @Embeddable
// public class BookAuthorId implements Serializable {

// private static final long serialVersionUID = 1L;

// @ManyToOne
// @JoinColumn(name = "book_id")
// private Book book;

// @ManyToOne
// @JoinColumn(name = "author_id")
// private Author author;

// public BookAuthorId(Book book, Author author) {
// this.book = book;
// this.author = author;
// }

// public Book getBook() {
// return book;
// }

// public void setBook(Book book) {
// this.book = book;
// }

// public Author getAuthor() {
// return author;
// }

// public void setAuthor(Author author) {
// this.author = author;
// }

// }
