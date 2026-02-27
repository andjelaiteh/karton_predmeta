/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.controller;

import com.andjela.karton_predmeta.dto.CreateStatusDto;
import com.andjela.karton_predmeta.entity.Predmet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.andjela.karton_predmeta.service.StatusService;

/**
 *
 * @author Andjela
 */
@RestController
@RequestMapping("/api/createstatus")
public class StatusController {
     private final StatusService createStatusService;

    public StatusController(StatusService createStatusService) {
        this.createStatusService = createStatusService;
    }

   @PostMapping
    public ResponseEntity<Predmet> create(@RequestBody CreateStatusDto dto) throws Exception {
    Predmet saved = createStatusService.create(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
}


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handle(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
