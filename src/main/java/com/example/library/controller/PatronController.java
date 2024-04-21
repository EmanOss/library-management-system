package com.example.library.controller;

import com.example.library.dto.PatronDto;
import com.example.library.mapper.Mapper;
import com.example.library.model.Patron;
import com.example.library.service.PatronService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patrons")
public class PatronController {
    private final PatronService patronService;
    private final Mapper<Patron, PatronDto> patronMapper;

    @Autowired
    public PatronController(PatronService patronService, Mapper<Patron, PatronDto> patronMapper) {
        this.patronService = patronService;
        this.patronMapper = patronMapper;
    }
    @GetMapping
    public ResponseEntity<List<Patron>> getPatrons() {
        return new ResponseEntity<>(this.patronService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatronById(@PathVariable Long id) {
        return new ResponseEntity<>(this.patronService.getById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<PatronDto> addPatron(@Valid @RequestBody PatronDto patronDto) {
        Patron patron = this.patronMapper.mapFromDto(patronDto);
        Patron savedPatron = this.patronService.save(patron);
        return new ResponseEntity<>(this.patronMapper.mapToDto(savedPatron), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PatronDto> updatePatron(@PathVariable Long id, @Valid @RequestBody PatronDto patronDto) {
        Patron updatedPatron = this.patronService.update(id, patronDto);
        return new ResponseEntity<>(this.patronMapper.mapToDto(updatedPatron), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id) {
        this.patronService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
