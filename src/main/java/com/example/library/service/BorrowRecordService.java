package com.example.library.service;

import com.example.library.model.BorrowRecord;

public interface BorrowRecordService {
    BorrowRecord getById(Long id);
    BorrowRecord borrowBook(Long bookId, Long patronId) throws Exception;
    BorrowRecord returnBook(Long bookId, Long patronId) throws Exception;
}
