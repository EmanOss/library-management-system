package com.example.library.service;

import com.example.library.model.Book;

import java.util.List;

public interface BookService {
    Book getById(Long id);
    List<Book> getAll();
    Book save(Book book);
    void delete(Long id);
    boolean isExists(Long id);
}
