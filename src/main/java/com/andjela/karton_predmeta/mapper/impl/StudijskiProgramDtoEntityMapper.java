/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.mapper.impl;

import com.andjela.karton_predmeta.dto.StudijskiProgramDto;
import com.andjela.karton_predmeta.entity.StudijskiProgram;
import com.andjela.karton_predmeta.mapper.DtoEntityMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author Andjela
 */
@Component
public class StudijskiProgramDtoEntityMapper implements DtoEntityMapper<StudijskiProgramDto, StudijskiProgram> {

    @Override
    public StudijskiProgramDto toDto(StudijskiProgram e) {
       if (e == null) return null;
        return new StudijskiProgramDto(e.getId(), e.getNaziv());
    }

    @Override
    public StudijskiProgram toEntity(StudijskiProgramDto t) {
         if (t == null) return null;
        return new StudijskiProgram(t.getId(), t.getNaziv()); }
    
}
