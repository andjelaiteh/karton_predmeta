/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.service.impl;

import com.andjela.karton_predmeta.dto.PredmetDto;
import com.andjela.karton_predmeta.entity.Predmet;
import com.andjela.karton_predmeta.mapper.impl.PredmetDtoEntityMapper;
import com.andjela.karton_predmeta.repository.AngazovanjeRepository;
import com.andjela.karton_predmeta.repository.NastavaRepository;
import com.andjela.karton_predmeta.repository.OcenjivanjeRepository;
import com.andjela.karton_predmeta.repository.PredmetLiteraturaRepository;
import com.andjela.karton_predmeta.repository.PredmetRepository;
import com.andjela.karton_predmeta.repository.StatusRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;

/**
 *
 * @author Andjela
 */

@ExtendWith(MockitoExtension.class)
class PredmetServiceImplTest {
        @Mock
    private PredmetRepository predmetRepository;

    @Mock
    private StatusRepository statusRepository;

    private PredmetServiceImpl predmetService;
    
    @Mock
    private AngazovanjeRepository angazovanjeRepository;
      
    @Mock
    private NastavaRepository nastavaRepository;
    @Mock
    private PredmetLiteraturaRepository predmetLiteraturaRepository;
    @Mock
    private OcenjivanjeRepository ocenjivanjeRepository;
    
    @BeforeEach
    void setUp() {
        PredmetDtoEntityMapper mapper = new PredmetDtoEntityMapper();
        predmetService = new PredmetServiceImpl(predmetRepository, mapper, statusRepository, angazovanjeRepository, nastavaRepository, predmetLiteraturaRepository, ocenjivanjeRepository);
    }
    
    @Test
    void save_uspesnoCuvaPredmet()  {
        PredmetDto dto = new PredmetDto();
        dto.setId(1L);
        dto.setNaziv("Automatizacija razvoja softvera");
        dto.setEspb(6);

        Predmet predmet = new Predmet();
        predmet.setId(1L);
        predmet.setNaziv("Automatizacija razvoja softvera");
        predmet.setEspb(6);

        when(predmetRepository.save(any(Predmet.class)))
                .thenReturn(predmet);

        PredmetDto rezultat = predmetService.save(dto);

        assertEquals("Automatizacija razvoja softvera", rezultat.getNaziv());
        assertEquals(6, rezultat.getEspb());

        verify(predmetRepository).save(any(Predmet.class));
    }

    @Test
    void save_bacaExceptionKadaNazivNijeUnet() {
        PredmetDto dto = new PredmetDto();
        dto.setNaziv("");
        dto.setEspb(6);

        Exception exception = assertThrows(Exception.class, () -> {
            predmetService.save(dto);
        });

        assertEquals("Naziv predmeta je obavezan.", exception.getMessage());
    }

    @Test
    void save_bacaExceptionKadaEspbNijePozitivan() {
        PredmetDto dto = new PredmetDto();
        dto.setNaziv("Automatizacija razvoja softvera");
        dto.setEspb(0);

        Exception exception = assertThrows(Exception.class, () -> {
            predmetService.save(dto);
        });

        assertEquals("ESPB mora biti pozitivan broj.", exception.getMessage());
    }

}
