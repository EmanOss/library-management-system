package com.example.library.service.Impl;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Override
    public Book getBookById(Long id) {
        return this.bookRepository.findById(id).orElse(null);
    }

    @Override
    public List<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book updateBook(Long id, String title, String author, int publicationYear, String ISBN) {
        Book book = this.bookRepository.findById(id).orElse(null);
        if (book == null) {
            return null;
        }
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublicationYear(publicationYear);
        book.setISBN(ISBN);
        return this.bookRepository.save(book);
    }

    @Override
    public Book addBook(String title, String author, int publicationYear, String ISBN) {
        Book book = Book.builder()
                .title(title)
                .author(author)
                .publicationYear(publicationYear)
                .ISBN(ISBN)
                .build();
        return this.bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        this.bookRepository.deleteById(id);
    }
}
