package com.example.library.dto;

import com.example.library.model.BorrowRecord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private int publicationYear;
    private String isbn;
    private Set<BorrowRecord> bookPatrons = new HashSet<>();
}
