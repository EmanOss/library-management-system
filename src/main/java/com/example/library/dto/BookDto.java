package com.example.library.dto;

import jakarta.validation.constraints.*;
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
public class BookDto {
    private Long id;
    @NotBlank(message = "Title must not be empty")
    private String title;
    @NotBlank(message = "Author name must not be empty")
    private String author;
    @Min(value = 1000, message = "Publication year must be at least 1000")
    @Max(value = 9999, message = "Publication year must be at most four digits")
    private int publicationYear;
    @NotBlank(message = "ISBN must not be empty")
    @Pattern(regexp = "^[0-9X-]+$", message = "ISBN can only contain numbers, dashes, and X characters")
    private String isbn;
    private boolean available = true;
    private Set<Long> borrowRecordsIds = new HashSet<>();
}
