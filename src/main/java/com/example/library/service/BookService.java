package com.example.library.service;

import com.example.library.model.Book;

import java.util.List;

public interface BookService {
    Book getBookById(Long id);
    List<Book> getAllBooks();
    Book updateBook(Long id, String title, String author, int publicationYear, String ISBN);
    Book addBook(String title, String author, int publicationYear, String ISBN);
    void deleteBook(Long id);
}
