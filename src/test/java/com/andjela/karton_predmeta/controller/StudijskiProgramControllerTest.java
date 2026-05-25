/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.controller;

import com.andjela.karton_predmeta.dto.StudijskiProgramDto;
import com.andjela.karton_predmeta.service.StudijskiProgramService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author Andjela
 */

@ExtendWith(MockitoExtension.class)
class StudijskiProgramControllerTest {
    
    @Mock
    private StudijskiProgramService service;

    @InjectMocks
    private StudijskiProgramController controller;

    @Test
    void findAll_vracaSvePrograme() {

        StudijskiProgramDto dto = new StudijskiProgramDto();
        dto.setId(1L);
        dto.setNaziv("Informacioni sistemi");

        when(service.findAll()).thenReturn(List.of(dto));

        List<StudijskiProgramDto> rezultat = controller.findAll();

        assertEquals(1, rezultat.size());
        assertEquals("Informacioni sistemi",
                rezultat.get(0).getNaziv());

        verify(service).findAll();
    }
    
}
