package com.example.library.service.Impl;

import com.example.library.model.Book;
import com.example.library.model.BorrowRecord;
import com.example.library.model.Patron;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowRecordRepository;
import com.example.library.repository.PatronRepository;
import com.example.library.service.BorrowRecordService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BorrowRecordServiceImpl implements BorrowRecordService {
    private final BorrowRecordRepository borrowRecordRepository;
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;

    @Autowired
    public BorrowRecordServiceImpl(BorrowRecordRepository borrowRecordRepository, BookRepository bookRepository, PatronRepository patronRepository) {
        this.borrowRecordRepository = borrowRecordRepository;
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }

    @Override
    public BorrowRecord getById(Long id) {
        return this.borrowRecordRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Borrow record not found"));
    }

    @Override
    @Transactional
    public synchronized BorrowRecord borrowBook(Long bookId, Long patronId) throws Exception {
        Book book = this.bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("No Book with such ID"));
        if (!book.isAvailable()) {
            throw new Exception("Book is currently not available");
        }
        Patron patron = this.patronRepository.findById(patronId)
                .orElseThrow(() -> new IllegalArgumentException("No Patron with such ID"));
        if (!patron.isCanBorrow()) {
            throw new Exception("Patron is currently not eligible to borrow books");
        }
        BorrowRecord borrowRecord = BorrowRecord.builder()
                .book(book)
                .patron(patron)
                .borrowDate(LocalDate.now())
                .build();
        BorrowRecord savedBorrowRecord = this.borrowRecordRepository.save(borrowRecord);
        book.setAvailable(false);
        patron.setCanBorrow(false);
        book.getBorrowRecordsIds().add(savedBorrowRecord.getId());
        patron.getBorrowRecordsIds().add(savedBorrowRecord.getId());
        return borrowRecord;
    }

    @Override
    public BorrowRecord returnBook(Long bookId, Long patronId) {
        Book book = this.bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("No Book with such ID"));
        Patron patron = this.patronRepository.findById(patronId)
                .orElseThrow(() -> new IllegalArgumentException("No Patron with such ID"));
        BorrowRecord borrowRecord = this.borrowRecordRepository.findByBookAndPatronAndReturnDateIsNull(book, patron);
        if (borrowRecord == null) {
            throw new EntityNotFoundException("Borrow record not found for this book and patron");
        }
        borrowRecord.setReturnDate(LocalDate.now());
        book.setAvailable(true);
        patron.setCanBorrow(true);
        this.borrowRecordRepository.save(borrowRecord);
        return borrowRecord;
    }
}
