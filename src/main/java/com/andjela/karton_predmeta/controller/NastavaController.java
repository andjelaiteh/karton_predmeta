/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.controller;

import com.andjela.karton_predmeta.dto.CreateNastavaDto;
import com.andjela.karton_predmeta.service.NastavaService;
import exception.NotFoundException;
import exception.ValidationException;
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
@RequestMapping("/api/nastava")
public class NastavaController {
    private final NastavaService nastavaService;

    public NastavaController(NastavaService nastavaService) {
        this.nastavaService = nastavaService;
    }
      @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateNastavaDto dto){
        nastavaService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ExceptionHandler({ValidationException.class, NotFoundException.class})
    public ResponseEntity<String> handle(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
