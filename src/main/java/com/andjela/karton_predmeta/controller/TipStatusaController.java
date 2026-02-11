/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.controller;

import com.andjela.karton_predmeta.repository.TipStatusaRepository;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Andjela
 */
@RestController
@RequestMapping("/api/tip-statusa")
public class TipStatusaController {
      private final TipStatusaRepository repo;

    public TipStatusaController(TipStatusaRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public Object findAll() {
        return repo.findAll();
    }
}
