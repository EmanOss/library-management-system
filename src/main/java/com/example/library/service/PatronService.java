package com.example.library.service;

import com.example.library.model.Patron;

import java.util.List;

public interface PatronService {
    Patron getById(Long id);
    List<Patron> getAll();
    Patron save(Patron patron);
    void delete(Long id);
    boolean isExists(Long id);
}
