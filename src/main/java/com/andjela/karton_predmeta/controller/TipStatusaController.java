/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.controller;

import com.andjela.karton_predmeta.dto.TipStatusaDto;
import com.andjela.karton_predmeta.mapper.impl.TipStatusaDtoEntityMapper;
import com.andjela.karton_predmeta.repository.TipStatusaRepository;
import java.util.List;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Andjela
 */
@RestController
@RequestMapping("/api/tip-statusa")
public class TipStatusaController {
       private final TipStatusaRepository repo;
    private final TipStatusaDtoEntityMapper mapper;

    public TipStatusaController(TipStatusaRepository repo, TipStatusaDtoEntityMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @GetMapping
    public List<TipStatusaDto> findAll() {
        return repo.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
