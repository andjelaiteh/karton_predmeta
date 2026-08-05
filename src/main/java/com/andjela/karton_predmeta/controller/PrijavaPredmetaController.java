/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.controller;


import com.andjela.karton_predmeta.service.PrijavaPredmetaService;
import java.util.List;
import java.util.Map;
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
@RequestMapping("/api/prijava-predmeta")
public class PrijavaPredmetaController {
    
    private final PrijavaPredmetaService prijavaPredmetaService;

    public PrijavaPredmetaController(PrijavaPredmetaService prijavaPredmetaService) {
        this.prijavaPredmetaService = prijavaPredmetaService;
    }

    @GetMapping("/podaci-korisnika")
    public Map<String, Object> podaciKorisnika() {
        return prijavaPredmetaService.podaciKorisnika();
    }

    @GetMapping("/obavezni")
    public List<Map<String, Object>> obavezni() {
        return prijavaPredmetaService.obavezniPredmeti();
    }

    @GetMapping("/izborni")
    public List<Map<String, Object>> izborni() {
        return prijavaPredmetaService.izborniPredmeti();
    }

    @PostMapping("/sacuvaj")
    public ResponseEntity<String> sacuvaj(@RequestBody Map<String, List<Long>> telo) {
        try {
            prijavaPredmetaService.sacuvajPrijavu(telo.get("izborniIds"));
            return ResponseEntity.ok("Prijava je uspešno sačuvana.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
