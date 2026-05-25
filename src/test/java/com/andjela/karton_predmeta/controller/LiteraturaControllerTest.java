/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.controller;


import com.andjela.karton_predmeta.dto.CreatePredmetLiteraturaDto;
import com.andjela.karton_predmeta.service.LiteraturaService;
import exception.NotFoundException;
import exception.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author Andjela
 */

@ExtendWith(MockitoExtension.class)
public class LiteraturaControllerTest {
    
    @Mock
    private LiteraturaService service;

    @InjectMocks
    private LiteraturaController controller;

    @Test
    void create_vracaCreatedStatus() {

        CreatePredmetLiteraturaDto dto =
                new CreatePredmetLiteraturaDto();

        ResponseEntity<Void> response = controller.create(dto);

        assertEquals(201, response.getStatusCode().value());

        verify(service).createForPredmet(dto);
    }

    @Test
    void handle_validationException_vracaBadRequest() {

        ValidationException exception =
                new ValidationException("Greška validacije");

        ResponseEntity<String> response =
                controller.handle(exception);

        assertEquals(400, response.getStatusCode().value());
        assertEquals("Greška validacije", response.getBody());
    }

    @Test
    void handle_notFoundException_vracaBadRequest() {

        NotFoundException exception =
                new NotFoundException("Nije pronađeno");

        ResponseEntity<String> response =
                controller.handle(exception);

        assertEquals(400, response.getStatusCode().value());
        assertEquals("Nije pronađeno", response.getBody());
    }
}
