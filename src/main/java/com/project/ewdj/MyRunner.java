package com.project.ewdj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.project.ewdj.entity.Book;
import com.project.ewdj.repository.BookRepository;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private BookRepository repository;

    // @Autowired
    // private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        repository.save(new Book("inferno op zee", 534, 23.4, "Clive CLussler"));
        repository.save(new Book("Dune", 531234, 23.4, "Frank Herbert"));
        repository.save(new Book("Children of Dune", 5324, 23.4, "Frank Herbert"));
        repository.save(new Book("Foundation", 5342344, 23.4, "Isaac Asimov"));

        // userRepository.save(new User("admin", "admin123", Role.ADMIN));
        // userRepository.save(new User("user", "user123", Role.USER));
        // userRepository.save(new User("admin1", "admin123", Role.ADMIN));
        // userRepository.save(new User("user1", "user123", Role.USER));
    }
}