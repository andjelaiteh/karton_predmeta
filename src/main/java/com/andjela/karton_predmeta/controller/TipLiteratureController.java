/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.controller;

import com.andjela.karton_predmeta.entity.TipLiterature;
import com.andjela.karton_predmeta.repository.TipLiteratureRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Andjela
 */
@RestController
@RequestMapping("/api/tip-literature")
public class TipLiteratureController {
    private final TipLiteratureRepository repository;

    public TipLiteratureController(TipLiteratureRepository repository) {
        this.repository = repository;
    }
     
    @GetMapping
    public List<TipLiterature> getAll() {
        return repository.findAll();
    }
}
