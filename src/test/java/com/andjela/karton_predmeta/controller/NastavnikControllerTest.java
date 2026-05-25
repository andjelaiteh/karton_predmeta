/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.controller;

import com.andjela.karton_predmeta.dto.DodajNastavnikeNaPredmetDto;
import com.andjela.karton_predmeta.dto.NastavnikDto;
import com.andjela.karton_predmeta.service.NastavnikAngazovanjaService;
import java.util.List;
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
class NastavnikControllerTest {
    
    @Mock
    private NastavnikAngazovanjaService service;

    @InjectMocks
    private NastavnikController controller;

    @Test
    void getAll_vracaSveNastavnike() {

        NastavnikDto nastavnik = new NastavnikDto();
        nastavnik.setId(1L);
        nastavnik.setIme("Petar");
        nastavnik.setPrezime("Petrovic");

        when(service.getAllNastavnici()).thenReturn(List.of(nastavnik));

        List<NastavnikDto> rezultat = controller.getAll();

        assertEquals(1, rezultat.size());
        assertEquals("Petar", rezultat.get(0).getIme());

        verify(service).getAllNastavnici();
    }

    @Test
    void dodaj_vracaCreatedStatus() {

        DodajNastavnikeNaPredmetDto dto = new DodajNastavnikeNaPredmetDto();
        dto.setPredmetId(1L);
        dto.setNastavnikIds(List.of(1L, 2L));

        ResponseEntity<Void> response = controller.dodaj(dto);

        assertEquals(201, response.getStatusCode().value());

        verify(service).dodajNastavnike(1L, List.of(1L, 2L));
    }
}
