package com.project.ewdj.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.ewdj.entity.Book;
import com.project.ewdj.entity.Favorite;
import com.project.ewdj.entity.User;

@Repository
public interface FavoriteRepository extends CrudRepository<Favorite, Long> {
    List<Favorite> findByUser(User user);

    List<Book> getBooksByUser(User user);

    void deleteByBookId(Long bookId);

}
