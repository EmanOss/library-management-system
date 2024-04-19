package com.example.library.service;

import com.example.library.dto.BookDto;
import com.example.library.model.Book;

import java.util.List;

public interface BookService {
    Book getBookById(Long id);
    List<Book> getAllBooks();
    Book saveBook(Book book);
    void deleteBook(Long id);
    boolean isExists(Long id);
}
