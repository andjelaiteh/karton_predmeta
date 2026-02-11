/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.controller;

import com.andjela.karton_predmeta.dto.StudijskiProgramDto;
import com.andjela.karton_predmeta.service.StudijskiProgramService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Andjela
 */
@RestController
@RequestMapping("/api/programi")
public class StudijskiProgramController {

   private final StudijskiProgramService service;

    public StudijskiProgramController(StudijskiProgramService service) {
        this.service = service;
    }

    @GetMapping
    public List<StudijskiProgramDto> findAll() {
        return service.findAll();
    }
}