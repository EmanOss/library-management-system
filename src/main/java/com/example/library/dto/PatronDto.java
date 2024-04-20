package com.example.library.dto;

import com.example.library.model.BorrowRecord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatronDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private boolean canBorrow = true;
    private Set<Long> borrowRecordsIds = new HashSet<>();
}
