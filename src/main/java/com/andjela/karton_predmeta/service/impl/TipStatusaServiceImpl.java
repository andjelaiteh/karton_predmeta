/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.service.impl;

import com.andjela.karton_predmeta.dto.TipStatusaDto;
import com.andjela.karton_predmeta.entity.TipStatusa;
import com.andjela.karton_predmeta.mapper.impl.TipStatusaDtoEntityMapper;
import com.andjela.karton_predmeta.repository.TipStatusaRepository;
import com.andjela.karton_predmeta.service.TipStatusaService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andjela
 */
@Service
public class TipStatusaServiceImpl implements TipStatusaService{

     private final TipStatusaRepository repository;
    private final TipStatusaDtoEntityMapper mapper;

    public TipStatusaServiceImpl(TipStatusaRepository repository, TipStatusaDtoEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    @Override
    public List<TipStatusaDto> findAll() {
        List<TipStatusa> list = repository.findAll();
        List<TipStatusaDto> dtos = new ArrayList<>();
        for (TipStatusa ts : list) dtos.add(mapper.toDto(ts));
        return dtos;}
    
}
