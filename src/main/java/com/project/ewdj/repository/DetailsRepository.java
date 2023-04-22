package com.project.ewdj.repository;

import com.project.ewdj.entity.Book;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface DetailsRepository extends CrudRepository<Book, Integer> {

}
