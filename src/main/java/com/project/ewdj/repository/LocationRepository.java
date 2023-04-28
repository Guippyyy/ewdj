package com.project.ewdj.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.ewdj.entity.Location;

@Repository
public interface LocationRepository extends CrudRepository<Location, Integer> {

}
