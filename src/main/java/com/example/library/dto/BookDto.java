package com.example.library.dto;

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
    private boolean available = true;
    private Set<Long> borrowRecordsIds = new HashSet<>();
}
