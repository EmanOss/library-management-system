package com.example.library.mapper.impl;

import com.example.library.dto.BookDto;
import com.example.library.mapper.Mapper;
import org.modelmapper.ModelMapper;
import com.example.library.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapperImpl implements Mapper<Book, BookDto> {
    private final ModelMapper modelMapper;

    public BookMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

        @Override
        public BookDto mapToDto(Book book) {
            return modelMapper.map(book, BookDto.class);
        }

        @Override
        public Book mapFromDto(BookDto bookDto) {
            return modelMapper.map(bookDto, Book.class);
        }

}
