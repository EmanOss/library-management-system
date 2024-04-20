package com.example.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class BorrowRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "borrow_date")
    private LocalDate borrowDate;
    @Column(name = "return_date", columnDefinition = "DATE")
    private LocalDate returnDate;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "patron_id")
    private Patron patron;
}
