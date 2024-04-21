package com.example.library.controller;

import com.example.library.dto.BookDto;
import com.example.library.mapper.Mapper;
import com.example.library.model.Book;
import com.example.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final Mapper<Book,BookDto> bookMapper;
    @Autowired
    public BookController(BookService bookService, Mapper<Book, BookDto> bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }
    @GetMapping
    public ResponseEntity<List<Book>> getBooks() {
        return new ResponseEntity<>(this.bookService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return new ResponseEntity<>(this.bookService.getById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<BookDto> addBook(@Valid @RequestBody BookDto bookDto) {
        Book book = this.bookMapper.mapFromDto(bookDto);
        Book savedBook = this.bookService.save(book);
        return new ResponseEntity<>(this.bookMapper.mapToDto(savedBook), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @Valid @RequestBody BookDto bookDto) {
        Book updatedBook = this.bookService.update(id, bookDto);
        return new ResponseEntity<>(this.bookMapper.mapToDto(updatedBook), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        this.bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
