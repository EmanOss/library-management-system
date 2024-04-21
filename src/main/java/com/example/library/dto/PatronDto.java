package com.example.library.dto;

import com.example.library.model.BorrowRecord;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @NotBlank(message = "Name must not be empty")
    private String name;
    @NotBlank(message = "Email must not be empty")
    @Email(message = "Email must be a valid email address")
    private String email;
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Phone must be a valid international phone number")
    private String phone;
    private boolean canBorrow = true;
    private Set<Long> borrowRecordsIds = new HashSet<>();
}
