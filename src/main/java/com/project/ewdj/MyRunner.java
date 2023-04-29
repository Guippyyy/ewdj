package com.project.ewdj;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.project.ewdj.entity.Author;
import com.project.ewdj.entity.Book;
import com.project.ewdj.entity.Location;
import com.project.ewdj.entity.Role;
import com.project.ewdj.entity.User;
import com.project.ewdj.repository.AuthorRepository;
import com.project.ewdj.repository.BookRepository;
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
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        Author author1 = new Author("Ian Banks");
        Author author2 = new Author("Venor Vinge");
        Author author3 = new Author("Frank Herbert");
        Author author4 = new Author("Isaac Asimov");

        Book book1 = new Book("Consider Phlebas", "2312865737457", new BigDecimal("32.0"), Arrays.asList(author1));
        Book book2 = new Book("Fire Upon The Deep", "2312365477328", new BigDecimal("32.0"), Arrays.asList(author2));
        Book book3 = new Book("Dune", "2312365460857", new BigDecimal("32.0"), Arrays.asList(author3));
        Book book4 = new Book("Foundation", "2312365467874", new BigDecimal("32.0"), Arrays.asList(author4));

        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(book4);

        lRepository.save(new Location("12312", "1231", "Ronse"));
        lRepository.save(new Location("12312", "1231", "Gent"));
        lRepository.save(new Location("12312", "1231", "Oudenaarde"));
        lRepository.save(new Location("12312", "1231", "Brussel"));

        Role roleA = new Role(TbConstants.Roles.ADMIN);
        Role roleB = new Role(TbConstants.Roles.USER);

        User user1 = new User("guillaume", "guillaume.decraene@outlook.com", passwordEncoder.encode("123"),
                Arrays.asList(roleA));
        User user2 = new User("guillaume", "guillaume.decraene@student.hogent.be", passwordEncoder.encode("abc"),
                Arrays.asList(roleB));

        uRepository.save(user1);
        uRepository.save(user2);
    }

}