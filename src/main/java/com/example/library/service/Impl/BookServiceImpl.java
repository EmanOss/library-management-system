package com.example.library.service.Impl;

import com.example.library.dto.BookDto;
import com.example.library.mapper.Mapper;
import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final Mapper<Book,BookDto> bookMapper;
    @Autowired
    public BookServiceImpl(BookRepository bookRepository, Mapper<Book, BookDto> bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }
    @Override
    public Book getById(Long id) {
        return this.bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book not found"));
    }

    @Override
    public List<Book> getAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book save(Book book) {
        return this.bookRepository.save(book);
    }
    @Override
    public Book update(Long id, BookDto bookDto) {
        if(!isExists(id)){
            throw new EntityNotFoundException("Book not found");
        }
        bookDto.setId(id);
        return this.save(this.bookMapper.mapFromDto(bookDto));
    }

    @Override
    public void delete(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return this.bookRepository.existsById(id);
    }
}
