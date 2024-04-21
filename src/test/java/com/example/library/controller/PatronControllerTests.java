package com.example.library.controller;

import com.example.library.dto.PatronDto;
import com.example.library.mapper.Mapper;
import com.example.library.model.Patron;
import com.example.library.service.PatronService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = PatronController.class)
public class PatronControllerTests {
    @MockBean
    private PatronService patronService;

    @MockBean
    private Mapper<Patron, PatronDto> patronMapper;

    @Autowired
    private PatronController patronController;

    // Mock data for testing
    private static final Long TEST_PATRON_ID = 1L;
    private static final Long NON_EXISTENT_PATRON_ID = 2L;
    private static final Patron TEST_PATRON = Patron.builder()
            .id(TEST_PATRON_ID)
            .email("TestEmail@email.com")
            .phone("+201010101010")
            .build();
    private static final PatronDto TEST_PATRON_DTO = PatronDto.builder()
            .id(TEST_PATRON_ID)
            .email("TestEmail@email.com")
            .phone("+201010101010")
            .build();

    @BeforeEach
    public void init() {
        when(patronMapper.mapFromDto(TEST_PATRON_DTO)).thenReturn(TEST_PATRON);
        when(patronMapper.mapToDto(TEST_PATRON)).thenReturn(TEST_PATRON_DTO);
    }

    @Test
    public void PatronController_CreatePatron_ReturnCreated() throws Exception {
        // Mock service method to save a patron
        when(patronService.save(TEST_PATRON)).thenReturn(TEST_PATRON);

        // Perform POST request
        ResponseEntity<PatronDto> response = patronController.addPatron(TEST_PATRON_DTO);

        // Verify response status and content
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(TEST_PATRON_DTO, response.getBody());
    }

    @Test
    public void PatronController_GetAllPatrons_ReturnsPatronsList() throws Exception {
        // Mock service method to return a list of patrons
        List<Patron> patrons = Arrays.asList(TEST_PATRON);
        when(patronService.getAll()).thenReturn(patrons);

        // Perform GET request
        ResponseEntity<List<Patron>> response = patronController.getPatrons();

        // Verify response status and content
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patrons, response.getBody());
    }

    @Test
    public void PatronController_GetPatronById_ReturnsPatron() {
        // Mock service method to return a patron by ID
        when(patronService.getById(TEST_PATRON_ID)).thenReturn(TEST_PATRON);

        // Perform GET request
        ResponseEntity<Patron> response = patronController.getPatronById(TEST_PATRON_ID);

        // Verify response status and content
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(TEST_PATRON, response.getBody());
    }

    @Test
    public void PatronController_UpdatePatron_UpdatesPatron() {
        // Mock service method to update a patron
        when(patronService.update(TEST_PATRON_ID, TEST_PATRON_DTO)).thenReturn(TEST_PATRON);

        // Perform PUT request
        ResponseEntity<PatronDto> response = patronController.updatePatron(TEST_PATRON_ID, TEST_PATRON_DTO);

        // Verify response status and content
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(TEST_PATRON_DTO, response.getBody());
    }

    @Test
    public void PatronController_DeletePatronById_DeletesPatron() {
        // Perform DELETE request
        ResponseEntity<Void> response = patronController.deletePatron(TEST_PATRON_ID);

        // Verify response status
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        // Verify that the service's delete method was called with the correct ID
        verify(patronService).delete(TEST_PATRON_ID);
    }
}
