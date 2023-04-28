package com.project.ewdj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ewdj.entity.FavoriteBook;
import com.project.ewdj.repository.FavoriteRepository;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository fRepo;

    public void saveAsFavorite(FavoriteBook f) {
        fRepo.save(f);
    }

    public List<FavoriteBook> getAllFavorites() {
        return (List<FavoriteBook>) fRepo.findAll();
    }

    public void deleteById(int id) {
        fRepo.deleteById(id);
    }
}
