/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.controller;

import com.andjela.karton_predmeta.dto.CreateStatusDto;
import com.andjela.karton_predmeta.entity.Predmet;
import com.andjela.karton_predmeta.service.StatusService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author Andjela
 */

@ExtendWith(MockitoExtension.class)
class StatusControllerTest {
    
    @Mock
    private StatusService createStatusService;

    @InjectMocks
    private StatusController controller;

    @Test
    void create_vracaCreatedStatus() {

        CreateStatusDto dto = new CreateStatusDto();

        Predmet predmet = new Predmet();
        predmet.setId(1L);

        when(createStatusService.create(dto)).thenReturn(predmet);

        ResponseEntity<Predmet> response = controller.create(dto);

        assertEquals(201, response.getStatusCode().value());
        assertEquals(predmet, response.getBody());

        verify(createStatusService).create(dto);
    }

    @Test
    void handle_vracaBadRequest() {

        Exception exception = new Exception("Greška");

        ResponseEntity<String> response =
                controller.handle(exception);

        assertEquals(400, response.getStatusCode().value());
        assertEquals("Greška", response.getBody());
    }
    
}
