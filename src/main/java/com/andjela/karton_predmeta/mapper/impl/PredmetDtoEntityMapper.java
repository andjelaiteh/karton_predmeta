/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.mapper.impl;

import com.andjela.karton_predmeta.dto.PredmetDto;
import com.andjela.karton_predmeta.entity.Predmet;
import com.andjela.karton_predmeta.mapper.DtoEntityMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author Andjela
 */
@Component
public class PredmetDtoEntityMapper implements DtoEntityMapper<PredmetDto, Predmet> {

    @Override
    public PredmetDto toDto(Predmet e) {
        if (e == null) return null;
        return new PredmetDto(e.getId(), e.getNaziv(), e.getEspb());
    }

    @Override
    public Predmet toEntity(PredmetDto t) {
       if (t == null) return null;
        return new Predmet(t.getId(), t.getNaziv(), t.getEspb());
    }
    
}
