/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.service.impl;

import com.andjela.karton_predmeta.dto.StudijskiProgramDto;
import com.andjela.karton_predmeta.entity.StudijskiProgram;
import com.andjela.karton_predmeta.mapper.impl.StudijskiProgramDtoEntityMapper;
import com.andjela.karton_predmeta.repository.StudijskiProgramRepository;
import java.util.ArrayList;
import java.util.List;
import com.andjela.karton_predmeta.service.StudijskiProgramService;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andjela
 */
@Service
public class StudijskiProgramServiceImpl implements StudijskiProgramService {

    private final StudijskiProgramRepository repository;
    private final StudijskiProgramDtoEntityMapper mapper;

    public StudijskiProgramServiceImpl(StudijskiProgramRepository repository,
                                       StudijskiProgramDtoEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    
    @Override
    public List<StudijskiProgramDto> findAll() {
         List<StudijskiProgram> programs = repository.findAll();
        List<StudijskiProgramDto> dtos = new ArrayList<>();
        for (StudijskiProgram p : programs) {
            dtos.add(mapper.toDto(p));
        }
        return dtos;}
    
}
