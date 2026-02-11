/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.controller;

import com.andjela.karton_predmeta.dto.CreatePredmetLiteraturaDto;
import com.andjela.karton_predmeta.service.LiteraturaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Andjela
 */

@RestController
@RequestMapping("/api/literatura")
public class LiteraturaController {
    
    private final LiteraturaService service;

  public LiteraturaController(LiteraturaService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<Void> create(@RequestBody CreatePredmetLiteraturaDto dto) throws Exception {
    service.createForPredmet(dto);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handle(Exception e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
  }
    
}
