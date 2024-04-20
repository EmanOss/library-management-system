package com.example.library.repository;

import com.example.library.model.Book;
import com.example.library.model.BorrowRecord;
import com.example.library.model.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    BorrowRecord findByBookAndPatronAndReturnDateIsNull(Book book, Patron patron);
}
