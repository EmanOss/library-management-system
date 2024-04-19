package com.example.library.controller;

import com.example.library.dto.BookDto;
import com.example.library.mapper.Mapper;
import com.example.library.model.Book;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    private final BookService bookService;
    private final Mapper<Book,BookDto> bookMapper;
    @Autowired
    public BookController(BookService bookService, Mapper<Book, BookDto> bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        return new ResponseEntity<>(this.bookService.getAllBooks(), HttpStatus.OK);
    }
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return new ResponseEntity<>(this.bookService.getBookById(id), HttpStatus.OK);
    }
    @PostMapping("/books")
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) {
        Book book = this.bookMapper.mapFrom(bookDto);
        Book savedBook = this.bookService.saveBook(book);
        return new ResponseEntity<>(this.bookMapper.mapTo(savedBook), HttpStatus.CREATED);
    }
    @PutMapping("/books/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        if(!this.bookService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bookDto.setId(id);
        Book book = this.bookMapper.mapFrom(bookDto);
        Book updatedBook = this.bookService.saveBook(book);
        return new ResponseEntity<>(this.bookMapper.mapTo(updatedBook), HttpStatus.OK);
    }
}
