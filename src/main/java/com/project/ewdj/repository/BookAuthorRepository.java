package com.project.ewdj.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.ewdj.entity.BookAuthor;
import com.project.ewdj.entity.BookAuthorId;

@Repository
public interface BookAuthorRepository extends CrudRepository<BookAuthor, BookAuthorId> {
}
