package com.example.library.controller;

import com.example.library.dto.PatronDto;
import com.example.library.mapper.Mapper;
import com.example.library.model.Patron;
import com.example.library.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatronController {
    private final PatronService patronService;
    private final Mapper<Patron, PatronDto> patronMapper;

    @Autowired
    public PatronController(PatronService patronService, Mapper<Patron, PatronDto> patronMapper) {
        this.patronService = patronService;
        this.patronMapper = patronMapper;
    }
    @GetMapping("/patrons")
    public ResponseEntity<List<Patron>> getPatrons() {
        return new ResponseEntity<>(this.patronService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/patrons/{id}")
    public ResponseEntity<Patron> getPatronById(@PathVariable Long id) {
        if(!this.patronService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(this.patronService.getById(id), HttpStatus.OK);
    }
    @PostMapping("/patrons")
    public ResponseEntity<PatronDto> addPatron(@RequestBody PatronDto patronDto) {
        Patron patron = this.patronMapper.mapFrom(patronDto);
        Patron savedPatron = this.patronService.save(patron);
        return new ResponseEntity<>(this.patronMapper.mapTo(savedPatron), HttpStatus.CREATED);
    }
    @PutMapping("/patrons/{id}")
    public ResponseEntity<PatronDto> updatePatron(@PathVariable Long id, @RequestBody PatronDto patronDto) {
        if(!this.patronService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        patronDto.setId(id);
        Patron patron = this.patronMapper.mapFrom(patronDto);
        Patron updatedPatron = this.patronService.save(patron);
        return new ResponseEntity<>(this.patronMapper.mapTo(updatedPatron), HttpStatus.OK);
    }
    @DeleteMapping("/patrons/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id) {
        if(!this.patronService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.patronService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
