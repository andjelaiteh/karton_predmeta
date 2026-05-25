/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.controller;

import com.andjela.karton_predmeta.dto.PredmetDto;
import com.andjela.karton_predmeta.service.PredmetService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
/**
 *
 * @author Andjela
 */

@ExtendWith(MockitoExtension.class)
public class PredmetControllerTest {
    @Mock
    private PredmetService predmetService;

    @InjectMocks
    private PredmetController predmetController;

    @Test
    void save_vracaKreiranPredmet() {
        PredmetDto zahtev = new PredmetDto();
        zahtev.setId(1L);
        zahtev.setNaziv("Automatizacija razvoja softvera");
        zahtev.setEspb(6);

        PredmetDto sacuvan = new PredmetDto();
        sacuvan.setId(1L);
        sacuvan.setNaziv("Automatizacija razvoja softvera");
        sacuvan.setEspb(6);

        when(predmetService.save(zahtev)).thenReturn(sacuvan);

        ResponseEntity<PredmetDto> response = predmetController.save(zahtev);

        assertEquals(201, response.getStatusCode().value());
        assertEquals("Automatizacija razvoja softvera", response.getBody().getNaziv());
        assertEquals(6, response.getBody().getEspb());

        verify(predmetService).save(zahtev);
    }
}
