// package com.project.ewdj.entity;

// import java.io.Serializable;

// import jakarta.persistence.EmbeddedId;
// import jakarta.persistence.Entity;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.MapsId;
// import jakarta.persistence.Table;
// import lombok.AccessLevel;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// @Getter
// @Setter
// @NoArgsConstructor
// @Entity
// @Table(name = "books_authors")
// public class BookAuthor implements Serializable {

// private static final long serialVersionUID = 1L;

// @EmbeddedId
// private BookAuthorId id;

// @ManyToOne
// @MapsId("bookId")
// @JoinColumn(name = "book_id")
// private Book book;

// @ManyToOne
// @MapsId("authorId")
// @JoinColumn(name = "author_id")
// private Author author;

// public BookAuthor(Book book, Author author) {
// this.book = book;
// this.author = author;
// this.id = new BookAuthorId(book.getId(), author.getId());
// }

// }
