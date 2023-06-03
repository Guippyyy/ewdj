package com.project.ewdj.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.project.ewdj.entity.Book;
import com.project.ewdj.entity.Favorite;
import com.project.ewdj.entity.User;
import com.project.ewdj.repository.FavoriteRepository;
import com.project.ewdj.repository.UserRepository;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository fRepo;

    @Autowired
    private UserRepository uRepo;

    public List<Favorite> getFavorites() {
        return (List<Favorite>) fRepo.findAll();
    }

    public void saveAsFavorite(Book book) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = uRepo.findByEmail(currentPrincipalName);

        Favorite f = new Favorite(user, book);
        fRepo.save(f);
    }

    public List<Book> getAllFavorites() {
        List<Book> books = new ArrayList<>();
        for (Favorite f : fRepo.findAll()) {
            books.add(f.getBook());
        }

        return books;
    }

    public Set<Book> getUserFavorites() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = uRepo.findByEmail(currentPrincipalName);
        List<Favorite> favorites = fRepo.findByUser(user);

        // TODO: Can't this happen in one query?
        Set<Book> books = new HashSet<>();
        for (Favorite f : favorites) {
            books.add(f.getBook());
        }

        return books;
    }

    public void deleteById(Long id) {
        fRepo.deleteById(id);
    }
}
