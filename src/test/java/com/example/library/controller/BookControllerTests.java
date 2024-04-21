package com.example.library.controller;

import com.example.library.dto.BookDto;
import com.example.library.mapper.Mapper;
import com.example.library.model.Book;
import com.example.library.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(controllers = BookController.class)
public class BookControllerTests {
    @MockBean
    private BookService bookService;

    @MockBean
    private Mapper<Book, BookDto> bookMapper;

    @Autowired
    private BookController bookController;

    // Mock data for testing
    private static final Long TEST_BOOK_ID = 1L;
    private static final Long NON_EXISTENT_BOOK_ID = 2L;
    private static final Book TEST_BOOK = Book.builder()
            .id(TEST_BOOK_ID)
            .title("Test Book")
            .author("Test Author")
            .isbn("1234567890")
            .build();
    private static final BookDto TEST_BOOK_DTO = BookDto.builder()
            .id(TEST_BOOK_ID)
            .title("Test BookDto")
            .author("Test Author Dto")
            .isbn("1234567890")
            .build();

    @BeforeEach
    public void init() {
        when(bookMapper.mapFromDto(TEST_BOOK_DTO)).thenReturn(TEST_BOOK);
        when(bookMapper.mapToDto(TEST_BOOK)).thenReturn(TEST_BOOK_DTO);
    }

    @Test
    public void BookController_CreateBook_ReturnCreated() throws Exception {
        // Mock service method to save a book
        when(bookService.save(TEST_BOOK)).thenReturn(TEST_BOOK);

        // Perform POST request
        ResponseEntity<BookDto> response = bookController.addBook(TEST_BOOK_DTO);

        // Verify response status and content
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(TEST_BOOK_DTO, response.getBody());
    }

    @Test
    public void BookController_GetAllBooks_ReturnsBooksList() throws Exception {
        // Mock service method to return a list of books
        List<Book> books = Arrays.asList(TEST_BOOK);
        when(bookService.getAll()).thenReturn(books);

        // Perform GET request
        ResponseEntity<List<Book>> response = bookController.getBooks();

        // Verify response status and content
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(books, response.getBody());
    }

    @Test
    public void BookController_GetBookById_ReturnsBook() {
        // Mock service method to return a book by ID
        when(bookService.getById(TEST_BOOK_ID)).thenReturn(TEST_BOOK);

        // Perform GET request
        ResponseEntity<Book> response = bookController.getBookById(TEST_BOOK_ID);

        // Verify response status and content
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(TEST_BOOK, response.getBody());
    }

    @Test
    public void BookController_UpdateBook_UpdatesBook() {
        // Mock service method to update a book
        when(bookService.update(TEST_BOOK_ID, TEST_BOOK_DTO)).thenReturn(TEST_BOOK);

        // Perform PUT request
        ResponseEntity<BookDto> response = bookController.updateBook(TEST_BOOK_ID, TEST_BOOK_DTO);

        // Verify response status and content
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(TEST_BOOK_DTO, response.getBody());
    }

    @Test
    public void BookController_DeleteBookById_DeletesBook() {
        // Perform DELETE request
        ResponseEntity<Void> response = bookController.deleteBook(TEST_BOOK_ID);

        // Verify response status
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        // Verify that the service's delete method was called with the correct ID
        verify(bookService).delete(TEST_BOOK_ID);
    }
}
