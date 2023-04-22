package com.project.ewdj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ewdj.entity.Favorite;
import com.project.ewdj.repository.FavoriteRepository;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository fRepo;

    public void saveAsFavorite(Favorite f) {
        fRepo.save(f);
    }

    public List<Favorite> getAllFavorites() {
        return fRepo.findAll();
    }

    public void deleteById(int id) {
        fRepo.deleteById(id);
    }
}
