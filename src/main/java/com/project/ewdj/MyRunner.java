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
        repository.save(new Book(131, "inferno op zee", 534, 23.4, "dave"));
        repository.save(new Book(1231, "Dune", 531234, 23.4, "frank Herbert"));
        repository.save(new Book(1123, "Children of Dune", 5324, 23.4, "frank herbert"));

        // userRepository.save(new User("admin", "admin123", Role.ADMIN));
        // userRepository.save(new User("user", "user123", Role.USER));
        // userRepository.save(new User("admin1", "admin123", Role.ADMIN));
        // userRepository.save(new User("user1", "user123", Role.USER));
    }
}