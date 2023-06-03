package com.project.ewdj;

import com.project.ewdj.entity.Book;
import com.project.ewdj.restController.BookRestController;
import com.project.ewdj.service.AuthorService;
import com.project.ewdj.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class BookRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private BookRestController bookRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookRestController = new BookRestController(bookService, authorService);
        mockMvc = MockMvcBuilders.standaloneSetup(bookRestController).build();
    }

    @Test
    void testGetBookByISBN() throws Exception {
        String isbnCode = "1234567890";
        Book book = new Book();
        book.setIsbnCode(isbnCode);

        when(bookService.getBookByISBN(isbnCode)).thenReturn(book);

        mockMvc.perform(get("/rest/book/isbn/{isbnCode}", isbnCode))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbnCode").value(isbnCode));

        verify(bookService, times(1)).getBookByISBN(isbnCode);
        verifyNoMoreInteractions(bookService);
    }

}
