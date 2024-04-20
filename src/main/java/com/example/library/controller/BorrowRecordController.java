package com.example.library.controller;

import com.example.library.dto.BorrowRecordDto;
import com.example.library.mapper.Mapper;
import com.example.library.model.BorrowRecord;
import com.example.library.service.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BorrowRecordController {
    private final BorrowRecordService borrowRecordService;
    private final Mapper<BorrowRecord, BorrowRecordDto> borrowRecordMapper;
    @Autowired
    public BorrowRecordController(BorrowRecordService borrowRecordService, Mapper<BorrowRecord, BorrowRecordDto> borrowRecordMapper) {
        this.borrowRecordService = borrowRecordService;
        this.borrowRecordMapper = borrowRecordMapper;
    }
    @GetMapping("record/{id}")
    public ResponseEntity<BorrowRecordDto> getBorrowRecord(@PathVariable Long id) {
        BorrowRecord borrowRecord = this.borrowRecordService.getById(id);
        return new ResponseEntity<>(this.borrowRecordMapper.mapToDto(borrowRecord), HttpStatus.OK);
    }
    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowRecordDto> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) throws Exception {
        BorrowRecord borrowRecord = this.borrowRecordService.borrowBook(bookId, patronId);
        return new ResponseEntity<>(this.borrowRecordMapper.mapToDto(borrowRecord), HttpStatus.CREATED);
    }
    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowRecordDto> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) throws Exception {
        BorrowRecord borrowRecord = this.borrowRecordService.returnBook(bookId, patronId);
        return new ResponseEntity<>(this.borrowRecordMapper.mapToDto(borrowRecord), HttpStatus.OK);
    }

}
