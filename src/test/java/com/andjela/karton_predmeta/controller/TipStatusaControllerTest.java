/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.controller;

import com.andjela.karton_predmeta.controller.TipStatusaController;
import com.andjela.karton_predmeta.dto.TipStatusaDto;
import com.andjela.karton_predmeta.entity.TipStatusa;
import com.andjela.karton_predmeta.mapper.impl.TipStatusaDtoEntityMapper;
import com.andjela.karton_predmeta.repository.TipStatusaRepository;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author Andjela
 */

@ExtendWith(MockitoExtension.class)
public class TipStatusaControllerTest {
    @Mock
    private TipStatusaRepository repo;

    private TipStatusaController controller;

@BeforeEach
void setUp() {
    TipStatusaDtoEntityMapper mapper = new TipStatusaDtoEntityMapper();
    controller = new TipStatusaController(repo, mapper);
}
    @Test
    void findAll_vracaListuTipovaStatusa() {

        TipStatusa tip = new TipStatusa();
        tip.setId(1L);
        tip.setNaziv("Obavezan");

        TipStatusaDto dto = new TipStatusaDto();
        dto.setId(1L);
        dto.setNaziv("Obavezan");

        when(repo.findAll()).thenReturn(List.of(tip));

        List<TipStatusaDto> rezultat = controller.findAll();

        assertEquals(1, rezultat.size());
        assertEquals("Obavezan", rezultat.get(0).getNaziv());

        verify(repo).findAll();
    }
}
