/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.controller;

import com.andjela.karton_predmeta.entity.TipLiterature;
import com.andjela.karton_predmeta.repository.TipLiteratureRepository;
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
public class TipLiteratureControllerTest {
    
    @Mock
    private TipLiteratureRepository repository;

    @InjectMocks
    private TipLiteratureController controller;

    @Test
    void getAll_vracaSveTipoveLiterature() {

        TipLiterature tip = new TipLiterature();
        tip.setId(1L);
        tip.setNaziv("Knjiga");

        when(repository.findAll()).thenReturn(List.of(tip));

        List<TipLiterature> rezultat = controller.getAll();

        assertEquals(1, rezultat.size());
        assertEquals("Knjiga", rezultat.get(0).getNaziv());

        verify(repository).findAll();
    }
}
