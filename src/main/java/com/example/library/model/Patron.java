package com.example.library.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String email;
    private String phone;
    @OneToMany(mappedBy = "patron")
    private Set<BorrowRecord> bookPatrons = new HashSet<>();
}
