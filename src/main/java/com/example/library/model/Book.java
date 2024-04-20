package com.example.library.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private String author;
    private int publicationYear;
    private String isbn;
    private boolean available;
    @ElementCollection
    private Set<Long> borrowRecordsIds = new HashSet<>();
}
