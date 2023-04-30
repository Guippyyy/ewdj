package com.project.ewdj.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.ewdj.entity.Favorite;

@Repository
public interface FavoriteRepository extends CrudRepository<Favorite, Integer> {

}
