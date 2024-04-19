package com.example.library.mapper.impl;

import com.example.library.dto.BorrowRecordDto;
import com.example.library.mapper.Mapper;
import com.example.library.model.BorrowRecord;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BorrowRecordMapperImpl implements Mapper<BorrowRecord, BorrowRecordDto> {

    private final ModelMapper modelMapper;


    public BorrowRecordMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public BorrowRecordDto mapTo (BorrowRecord patron){
        return modelMapper.map(patron, BorrowRecordDto.class);
    }

    @Override
    public BorrowRecord mapFrom (BorrowRecordDto patronDto){
        return modelMapper.map(patronDto, BorrowRecord.class);
    }
}