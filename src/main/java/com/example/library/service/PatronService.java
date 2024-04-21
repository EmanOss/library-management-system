package com.example.library.service;

import com.example.library.dto.PatronDto;
import com.example.library.model.Patron;

import java.util.List;

public interface PatronService {
    Patron getById(Long id);
    List<Patron> getAll();
    Patron save(Patron patron);
    Patron update(Long id, PatronDto bookDto);
    void delete(Long id);
    boolean isExists(Long id);
}
