package com.example.library.dto;

import com.example.library.model.Book;
import com.example.library.model.Patron;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BorrowRecordDto {
    private Long id;
    private Date borrowDate;
    private Date returnDate;
    private Book book;
    private Patron patron;
}
