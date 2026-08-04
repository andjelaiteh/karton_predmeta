/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.andjela.karton_predmeta.service;

import com.andjela.karton_predmeta.dto.PredmetDetaljiDto;
import com.andjela.karton_predmeta.dto.PredmetDto;
import java.util.List;


/**
 *
 * @author Andjela
 */

public interface PredmetService {
    PredmetDto save(PredmetDto predmetDto);
    List<PredmetDto> findAll();
    PredmetDto findById(Long id);
    PredmetDetaljiDto findDetaljiById(Long id);
    void obrisiDetaljePredmeta(Long id);
    void obrisiPredmet(Long id);
    void izmeniOsnovnePodatke(Long id, String naziv, Integer espb, Long programId, Long tipStatusaId);
}
