/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.mapper.impl;

import com.andjela.karton_predmeta.dto.TipStatusaDto;
import com.andjela.karton_predmeta.entity.TipStatusa;
import com.andjela.karton_predmeta.mapper.DtoEntityMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author Andjela
 */
@Component
public class TipStatusaDtoEntityMapper implements DtoEntityMapper<TipStatusaDto, TipStatusa>{

    @Override
    public TipStatusaDto toDto(TipStatusa e) {
        if (e == null) return null;
        return new TipStatusaDto(e.getId(), e.getNaziv());
    }

    @Override
    public TipStatusa toEntity(TipStatusaDto t) {
         if (t == null) return null;
        return new TipStatusa(t.getId(), t.getNaziv());  }
    
}
