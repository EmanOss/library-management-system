package com.example.library.service.Impl;

import com.example.library.dto.PatronDto;
import com.example.library.mapper.Mapper;
import com.example.library.model.Patron;
import com.example.library.repository.PatronRepository;
import com.example.library.service.PatronService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatronServiceImpl implements PatronService {
    private final PatronRepository patronRepository;
    private final Mapper<Patron, PatronDto> patronMapper;
    @Autowired
    public PatronServiceImpl(PatronRepository patronRepository, Mapper<Patron, PatronDto> patronMapper) {
        this.patronRepository = patronRepository;
        this.patronMapper = patronMapper;
    }

    @Override
    public Patron getById(Long id) {
        return this.patronRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Patron not found"));
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
    public Patron update(Long id, PatronDto patronDto) {
        if(!isExists(id)){
            throw new EntityNotFoundException("Patron not found");
        }
        patronDto.setId(id);
        return this.save(this.patronMapper.mapFromDto(patronDto));
    }

    @Override
    public void delete(Long id) {
        try{
            this.getById(id);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Patron not found");
        }
        this.patronRepository.deleteById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return this.patronRepository.existsById(id);
    }
}
