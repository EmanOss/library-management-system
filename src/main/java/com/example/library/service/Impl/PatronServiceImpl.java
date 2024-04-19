package com.example.library.service.Impl;

import com.example.library.model.Patron;
import com.example.library.repository.PatronRepository;
import com.example.library.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatronServiceImpl implements PatronService {
    private PatronRepository patronRepository;
    @Autowired
    public PatronServiceImpl(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    @Override
    public Patron getById(Long id) {
        return this.patronRepository.findById(id).orElse(null);
    }

    @Override
    public List<Patron> getAll() {
        return this.patronRepository.findAll();
    }

    @Override
    public Patron save(Patron patron) {
        return this.patronRepository.save(patron);
    }

    @Override
    public void delete(Long id) {
        this.patronRepository.deleteById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return this.patronRepository.existsById(id);
    }
}
