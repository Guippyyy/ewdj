package com.project.ewdj;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.project.ewdj.entity.Author;
import com.project.ewdj.entity.Book;
import com.project.ewdj.entity.Favorite;
import com.project.ewdj.entity.Location;
import com.project.ewdj.entity.Role;
import com.project.ewdj.entity.User;
import com.project.ewdj.repository.AuthorRepository;
import com.project.ewdj.repository.BookRepository;
import com.project.ewdj.repository.FavoriteRepository;
import com.project.ewdj.repository.LocationRepository;
import com.project.ewdj.repository.UserRepository;
import com.project.ewdj.util.TbConstants;

@Component
public class MyRunner implements CommandLineRunner {

        @Autowired
        private BookRepository repository;

        @Autowired
        private AuthorRepository aRepository;

        @Autowired
        private UserRepository uRepository;

        @Autowired
        private LocationRepository lRepository;

        @Autowired
        private FavoriteRepository fRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Override
        public void run(String... args) throws Exception {

                Author author1 = new Author("Ian Banks");
                Author author2 = new Author("Venor Vinge");
                Author author3 = new Author("Frank Herbert");
                Author author4 = new Author("Isaac Asimov");

                aRepository.save(author1);
                aRepository.save(author2);
                aRepository.save(author3);
                aRepository.save(author4);

                Book book1 = new Book("Consider Phlebas", "2312865737457", new BigDecimal("32.0"));
                Book book2 = new Book("Fire Upon The Deep", "2312365477328", new BigDecimal("32.0"));
                Book book3 = new Book("Dune", "2312365460857", new BigDecimal("32.0"));
                Book book4 = new Book("Foundation", "2312365467874", new BigDecimal("32.0"));
                Book book5 = new Book("Player Of Games", "2312865737457", new BigDecimal("32.0"));

                book1.getAuthors().add(author1);
                book1.getAuthors().add(author2);

                book5.getAuthors().add(author1);
                book1.getAuthors().add(author3);
                book2.getAuthors().add(author2);
                book3.getAuthors().add(author3);
                book5.getAuthors().add(author4);
                author1.getBooks().add(book1);
                author1.getBooks().add(book5);

                author1.getBooks().add(book1);
                author1.getBooks().add(book5);
                author2.getBooks().add(book1);

                repository.save(book1);
                repository.save(book2);
                repository.save(book3);
                repository.save(book4);
                repository.save(book5);

                // repository.assignAuthorToBook(book1.getId(), author1.getId());

                lRepository.save(new Location("12312", "1231", "Ronse"));
                lRepository.save(new Location("12312", "1231", "Gent"));
                lRepository.save(new Location("12312", "1231", "Oudenaarde"));
                lRepository.save(new Location("12312", "1231", "Brussel"));

                Role roleA = new Role(TbConstants.Roles.ADMIN);
                Role roleB = new Role(TbConstants.Roles.USER);

                User user1 = new User("guillaume", "guillaume.decraene@outlook.com", passwordEncoder.encode("123"),
                                Arrays.asList(roleA));
                User user2 = new User("guillaume", "guillaume.decraene@student.hogent.be",
                                passwordEncoder.encode("abc"),
                                Arrays.asList(roleB));

                uRepository.save(user1);
                uRepository.save(user2);

                // Favorite favorite1 = new Favorite(user2, book1);
                // Favorite favorite2 = new Favorite(user2, book2);
                // fRepository.save(favorite1);
                // fRepository.save(favorite2);

        }

}