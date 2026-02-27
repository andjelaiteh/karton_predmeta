/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.service.impl;

import com.andjela.karton_predmeta.dto.CreateLiteraturaStavkaDto;
import com.andjela.karton_predmeta.dto.CreatePredmetLiteraturaDto;
import com.andjela.karton_predmeta.entity.Autor;
import com.andjela.karton_predmeta.entity.AutorLiteratura;
import com.andjela.karton_predmeta.entity.Izdavac;
import com.andjela.karton_predmeta.entity.Literatura;
import com.andjela.karton_predmeta.entity.Predmet;
import com.andjela.karton_predmeta.entity.PredmetLiteratura;
import com.andjela.karton_predmeta.entity.TipLiterature;
import com.andjela.karton_predmeta.repository.AutorLiteraturaRepository;
import com.andjela.karton_predmeta.repository.AutorRepository;
import com.andjela.karton_predmeta.repository.IzdavacRepository;
import com.andjela.karton_predmeta.repository.LiteraturaRepository;
import com.andjela.karton_predmeta.repository.PredmetLiteraturaRepository;
import com.andjela.karton_predmeta.repository.PredmetRepository;
import com.andjela.karton_predmeta.repository.TipLiteratureRepository;
import com.andjela.karton_predmeta.service.LiteraturaService;
import jakarta.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andjela
 */

@Service
public class LiteraturaServiceImpl implements LiteraturaService{

  private final PredmetRepository predmetRepo;
  private final LiteraturaRepository literaturaRepo;
  private final PredmetLiteraturaRepository predmetLiteraturaRepo;
  private final TipLiteratureRepository tipRepo;
  private final IzdavacRepository izdavacRepo;
  private final AutorLiteraturaRepository autorliteraturaRepo;
  private final AutorRepository autorRepo;

    public LiteraturaServiceImpl(PredmetRepository predmetRepo, LiteraturaRepository literaturaRepo, PredmetLiteraturaRepository predmetLiteraturaRepo, TipLiteratureRepository tipRepo, IzdavacRepository izdavacRepo, AutorLiteraturaRepository autorliteraturaRepo, AutorRepository autorRepo) {
        this.predmetRepo = predmetRepo;
        this.literaturaRepo = literaturaRepo;
        this.predmetLiteraturaRepo = predmetLiteraturaRepo;
        this.tipRepo = tipRepo;
        this.izdavacRepo = izdavacRepo;
        this.autorliteraturaRepo = autorliteraturaRepo;
        this.autorRepo = autorRepo;
    }
  
  


    

    @Override
    @Transactional
    public void createForPredmet(CreatePredmetLiteraturaDto dto) {
        if (dto == null) throw new RuntimeException("DTO je null");
        if (dto.getPredmetId() == null) throw new RuntimeException("predmetId je obavezan");
        if (dto.getStavke() == null || dto.getStavke().isEmpty())
            throw new RuntimeException("stavke su obavezne");

        Predmet predmet = predmetRepo.findById(dto.getPredmetId())
                .orElseThrow(() -> new RuntimeException("Predmet ne postoji"));

        for (CreateLiteraturaStavkaDto s : dto.getStavke()) {
            validate(s);

            TipLiterature tip = tipRepo.findById(s.getTipLiteratureId())
                    .orElseThrow(() -> new RuntimeException("Tip literature ne postoji"));

            Literatura lit = new Literatura();
            lit.setNaslov(s.getNaslov());
            lit.setGodina(s.getGodina());
            
           String naziv = s.getIzdavacNaziv().trim();
           Izdavac izdavac = izdavacRepo.findByNaziv(naziv)
                   .orElseGet(() -> {
            Izdavac novi = new Izdavac();
            novi.setNaziv(naziv);
            return izdavacRepo.save(novi);
        });
           lit.setIzdavac(izdavac);


            lit.setIzdavac(izdavac);
            
            lit = literaturaRepo.save(lit);

            PredmetLiteratura veza = new PredmetLiteratura();
            veza.setPredmet(predmet);
            veza.setLiteratura(lit);
            veza.setTipLiterature(tip);

            predmetLiteraturaRepo.save(veza);
            
            List<String> autori = s.getAutori();
            
            if (autori != null) {

                for (String ime : autori) {

                    if (ime == null || ime.trim().isEmpty()) continue;

                    String cleanIme = ime.trim();

                    Autor autor = autorRepo.findByImePrezime(cleanIme)
                            .orElseGet(() -> {
                                Autor novi = new Autor();
                                novi.setImePrezime(cleanIme);
                                return autorRepo.save(novi);
                            });

                    AutorLiteratura vezaAutor = new AutorLiteratura();
                    vezaAutor.setAutor(autor);
                    vezaAutor.setLiteratura(lit);

                    autorliteraturaRepo.save(vezaAutor);
    }
}
    }}

    private void validate(CreateLiteraturaStavkaDto s) {
        if (s == null) throw new RuntimeException("Stavka je null");
        if (s.getNaslov() == null || s.getNaslov().trim().isEmpty())
            throw new RuntimeException("naslov je obavezan");
        if (s.getGodina() == null || s.getGodina() < 0)
            throw new RuntimeException("godina je obavezna i mora biti >= 0");
        if (s.getIzdavacNaziv() == null || s.getIzdavacNaziv().trim().isEmpty())
            throw new RuntimeException("izdavacNaziv je obavezan");
        if (s.getTipLiteratureId() == null)
            throw new RuntimeException("tipLiteratureId je obavezan");
    }
    
  
    
}
