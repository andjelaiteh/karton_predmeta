/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.service.impl;

import com.andjela.karton_predmeta.dto.PredmetDto;
import com.andjela.karton_predmeta.entity.Predmet;
import com.andjela.karton_predmeta.mapper.impl.PredmetDtoEntityMapper;
import com.andjela.karton_predmeta.repository.PredmetRepository;
import com.andjela.karton_predmeta.service.PredmetService;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andjela
 */
@Service
public class PredmetServiceImpl implements PredmetService{

    private final PredmetRepository predmetRepository;
    private final PredmetDtoEntityMapper predmetMapper;

    public PredmetServiceImpl(PredmetRepository predmetRepository, PredmetDtoEntityMapper predmetMapper) {
        this.predmetRepository = predmetRepository;
        this.predmetMapper = predmetMapper;
    }
    @Override
    public PredmetDto save(PredmetDto predmetDto) throws Exception {
         if (predmetDto.getNaziv() == null || predmetDto.getNaziv().trim().isEmpty()) {
            throw new Exception("Naziv predmeta je obavezan.");
        }
        if (predmetDto.getEspb() == null || predmetDto.getEspb() <= 0) {
            throw new Exception("ESPB mora biti pozitivan broj.");
        }

        Predmet predmet = predmetMapper.toEntity(predmetDto);
        predmet = predmetRepository.save(predmet);
        return predmetMapper.toDto(predmet);}
    
}
