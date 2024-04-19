package com.example.library.mapper.impl;

import com.example.library.dto.PatronDto;
import com.example.library.model.Patron;
import com.example.library.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PatronMapperImpl implements Mapper<Patron, PatronDto> {

    private final ModelMapper modelMapper;

    public PatronMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PatronDto mapTo(Patron patron) {
        return modelMapper.map(patron, PatronDto.class);
    }

    @Override
    public Patron mapFrom(PatronDto patronDto) {
        return modelMapper.map(patronDto, Patron.class);
    }
}
