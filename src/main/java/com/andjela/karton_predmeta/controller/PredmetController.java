/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.controller;

import com.andjela.karton_predmeta.dto.PredmetDto;
import com.andjela.karton_predmeta.service.PredmetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Andjela
 */
@RestController
@RequestMapping("/api/predmet")
public class PredmetController {

    private final PredmetService predmetService;

    public PredmetController(PredmetService predmetService) {
        this.predmetService = predmetService;
    }

     @PostMapping
    public ResponseEntity<PredmetDto> save(@RequestBody PredmetDto dto) throws Exception {
        return new ResponseEntity<>(predmetService.save(dto), HttpStatus.CREATED);
    }

}
