/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.controller;

import com.andjela.karton_predmeta.dto.DodajNastavnikeNaPredmetDto;
import com.andjela.karton_predmeta.dto.NastavnikDto;
import com.andjela.karton_predmeta.entity.Nastavnik;
import com.andjela.karton_predmeta.repository.NastavnikRepository;
import com.andjela.karton_predmeta.service.NastavnikAngazovanjaService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Andjela
 */
@RestController
@RequestMapping("/api/nastavnici")
public class NastavnikController {
     private final NastavnikAngazovanjaService service;

    public NastavnikController(NastavnikAngazovanjaService service) {
        this.service = service;
    }

    // dropdown
    @GetMapping
    public List<NastavnikDto> getAll() {
        return service.getAllNastavnici();
    }

    // dodavanje na predmet
    @PostMapping("/angazovanje")
    public ResponseEntity<Void> dodaj(@RequestBody DodajNastavnikeNaPredmetDto dto)
            throws Exception {
        service.dodajNastavnike(dto.getPredmetId(), dto.getNastavnikIds());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
