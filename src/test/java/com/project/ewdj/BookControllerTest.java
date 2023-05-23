package com.project.ewdj;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.ewdj.controller.BookController;
import com.project.ewdj.dto.FormDto;
import com.project.ewdj.entity.Book;
import com.project.ewdj.entity.Favorite;
import com.project.ewdj.entity.Location;
import com.project.ewdj.service.BookService;
import com.project.ewdj.service.DetailsService;
import com.project.ewdj.service.FavoriteService;
import com.project.ewdj.service.LocationService;
import com.project.ewdj.util.FavoriteUtils;
import com.project.ewdj.util.HomeListItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @Mock
    private BookService bookService;

    @Mock
    private FavoriteService favoriteService;

    @Mock
    private LocationService locationService;

    @Mock
    private Model model;

    @Mock
    private DetailsService detailsService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testHome() {
        // Mock data
        List<Book> bookList = new ArrayList<>();
        Book book1 = new Book();
        bookList.add(book1);
        Set<Book> favorites = new HashSet<>();
        favorites.add(book1);

        // Mock behavior
        when(bookService.getAllBooks()).thenReturn(bookList);
        when(favoriteService.getUserFavorites()).thenReturn(favorites);

        // Perform the test
        ModelAndView modelAndView = bookController.home();

        // Verify the results
        assertEquals("home", modelAndView.getViewName());
        List<HomeListItem> items = (List<HomeListItem>) modelAndView.getModel().get("items");
        assertEquals(bookList.size(), items.size());

        verify(bookService, times(1)).getAllBooks();
        verify(favoriteService, times(1)).getUserFavorites();
    }

    @Test
    void testAddBook_ValidationErrors() {
        // Mock data
        FormDto formDto = new FormDto();
        Book book = new Book();
        BindingResult bindingResult = mock(BindingResult.class);
        Model model = mock(Model.class);

        formDto.setIsbnCode("1234567890");
        // Mock behavior
        when(bindingResult.hasErrors()).thenReturn(true);

        // Perform the test
        String result = bookController.addBook(book, model, formDto, bindingResult, null);

        // Verify the results
        assertEquals("add_book", result);
        verify(model, times(1)).addAttribute(eq("form"), any(FormDto.class));
        verify(bookService, never()).save(any(Book.class));
        verify(locationService, never()).save(any(Location.class));
    }

    @Test
    void testGetAllFavoriteBooks() {
        // Mock data
        Set<Book> favorites = new HashSet<>();
        Book book1 = new Book();
        Book book2 = new Book();
        favorites.add(book1);
        favorites.add(book2);
        Model model = mock(Model.class);

        // Mock behavior
        when(favoriteService.getUserFavorites()).thenReturn(favorites);

        // Perform the test
        String result = bookController.getAllFavoriteBooks(model);

        // Verify the results
        assertEquals("favoriteBookList", result);
        verify(model, times(1)).addAttribute("book", favorites);
    }

    @Test
    void testMostPop() {
        // Mock data
        Favorite favorite1 = new Favorite();
        favorite1.setBook(new Book());
        Favorite favorite2 = new Favorite();
        favorite2.setBook(new Book());
        List<Favorite> favorites = Arrays.asList(favorite1, favorite2);

        // Mock behavior
        when(favoriteService.getFavorites()).thenReturn(favorites);

        // Perform the test
        String viewName = bookController.mostPop(model);

        // Verify the results
        assertEquals("mostPop", viewName);
        verify(model, times(1)).addAttribute(eq("items"), anyList());
        verify(favoriteService, times(1)).getFavorites();
    }

    // @Test
    // void testShowDetails() {
    // // Mock data
    // long bookId = 1L;
    // Book book = new Book();
    // book.setId(bookId);

    // // Mock behavior
    // when(bookService.getBookById(bookId)).thenReturn(book);

    // // Perform the test
    // String viewName = bookController.showDetails(bookId, model);

    // // Verify the results
    // assertEquals("bookDetails", viewName);
    // verify(model, times(1)).addAttribute(eq("loc"), anyList());
    // verify(model, times(1)).addAttribute(eq("book"), eq(book));
    // verify(model, times(1)).addAttribute(eq("star"), anyInt());
    // verify(bookService, times(1)).getBookById(bookId);
    // }

    @Test
    void testDetails() {
        Model model = mock(Model.class);

        String viewName = bookController.details(model);

        assertEquals("bookDetails", viewName);
        verifyNoInteractions(bookService);
        verifyNoInteractions(detailsService);
        verifyNoInteractions(favoriteService);
        verifyNoInteractions(locationService);
        verifyNoMoreInteractions(model);
    }
}