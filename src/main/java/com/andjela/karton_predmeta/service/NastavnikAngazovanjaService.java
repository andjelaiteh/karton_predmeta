/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.andjela.karton_predmeta.service;

import com.andjela.karton_predmeta.dto.NastavnikDto;
import java.util.List;

/**
 *
 * @author Andjela
 */
public interface NastavnikAngazovanjaService {
     List<NastavnikDto> getAllNastavnici();
    void dodajNastavnike(Long predmetId, List<Long> nastavnikIds) throws Exception;

}
