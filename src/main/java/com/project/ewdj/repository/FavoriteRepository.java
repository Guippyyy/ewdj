package com.project.ewdj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.ewdj.entity.FavoriteBook;

@Repository
public interface FavoriteRepository extends CrudRepository<FavoriteBook, Integer> {

}
