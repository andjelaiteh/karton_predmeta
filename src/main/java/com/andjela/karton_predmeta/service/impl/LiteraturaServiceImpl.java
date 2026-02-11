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
import com.andjela.karton_predmeta.repository.AutorLiteraturaRepository;
import com.andjela.karton_predmeta.repository.AutorRepository;
import com.andjela.karton_predmeta.repository.IzdavacRepository;
import com.andjela.karton_predmeta.repository.LiteraturaRepository;
import com.andjela.karton_predmeta.repository.PredmetLiteraturaRepository;
import com.andjela.karton_predmeta.repository.PredmetRepository;
import com.andjela.karton_predmeta.service.LiteraturaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andjela
 */

@Service
public class LiteraturaServiceImpl implements LiteraturaService{

  private final PredmetRepository predmetRepo;
  private final AutorRepository autorRepo;
  private final IzdavacRepository izdavacRepo;
  private final LiteraturaRepository literaturaRepo;
  private final AutorLiteraturaRepository autorLiteraturaRepo;
  private final PredmetLiteraturaRepository predmetLiteraturaRepo;

  public LiteraturaServiceImpl(PredmetRepository predmetRepo,
                              AutorRepository autorRepo,
                              IzdavacRepository izdavacRepo,
                              LiteraturaRepository literaturaRepo,
                              AutorLiteraturaRepository autorLiteraturaRepo,
                              PredmetLiteraturaRepository predmetLiteraturaRepo) {
    this.predmetRepo = predmetRepo;
    this.autorRepo = autorRepo;
    this.izdavacRepo = izdavacRepo;
    this.literaturaRepo = literaturaRepo;
    this.autorLiteraturaRepo = autorLiteraturaRepo;
    this.predmetLiteraturaRepo = predmetLiteraturaRepo;
  }

  @Override
  @Transactional
  public void createForPredmet(CreatePredmetLiteraturaDto dto) throws Exception {
    if (dto == null) throw new Exception("Request body je obavezan.");
    if (dto.getPredmetId() == null) throw new Exception("predmetId je obavezan.");
    if (dto.getStavke() == null || dto.getStavke().isEmpty())
      throw new Exception("stavke su obavezne.");

    Predmet predmet = predmetRepo.findById(dto.getPredmetId())
        .orElseThrow(() -> new Exception("Predmet ne postoji."));

    for (CreateLiteraturaStavkaDto s : dto.getStavke()) {
      validateStavka(s);

      Izdavac izdavac = findOrCreateIzdavac(s.getIzdavacNaziv());
      Literatura lit = new Literatura();
      lit.setNaslov(s.getNaslov().trim());
      lit.setGodina(s.getGodina());
      lit.setIzdavac(izdavac);
      lit = literaturaRepo.save(lit);

      PredmetLiteratura pl = new PredmetLiteratura();
      pl.setPredmet(predmet);
      pl.setLiteratura(lit);
      pl.setObavezna(s.getObavezna());
      predmetLiteraturaRepo.save(pl);

      for (String autorIme : s.getAutoriImePrezime()) {
        Autor autor = findOrCreateAutor(autorIme);

        AutorLiteratura al = new AutorLiteratura();
        al.setAutor(autor);
        al.setLiteratura(lit);
        autorLiteraturaRepo.save(al);
      }
    }
  }

  private void validateStavka(CreateLiteraturaStavkaDto s) throws Exception {
    if (s == null) throw new Exception("stavka ne sme biti null.");
    if (s.getNaslov() == null || s.getNaslov().trim().isEmpty())
      throw new Exception("naslov je obavezan.");
    if (s.getGodina() == null || s.getGodina() < 0)
      throw new Exception("godina je obavezna i mora biti >= 0.");
    if (s.getIzdavacNaziv() == null || s.getIzdavacNaziv().trim().isEmpty())
      throw new Exception("izdavacNaziv je obavezan.");
    if (s.getObavezna() == null)
      throw new Exception("obavezna mora biti true/false.");
    if (s.getAutoriImePrezime() == null || s.getAutoriImePrezime().isEmpty())
      throw new Exception("moras uneti bar jednog autora.");
  }

  private Izdavac findOrCreateIzdavac(String naziv) {
    String n = naziv.trim();
    for (Izdavac i : izdavacRepo.findAll()) {
      if (i.getNaziv() != null && i.getNaziv().equalsIgnoreCase(n)) return i;
    }
    Izdavac novi = new Izdavac();
    novi.setNaziv(n);
    return izdavacRepo.save(novi);
  }

  private Autor findOrCreateAutor(String imePrezime) throws Exception {
    if (imePrezime == null || imePrezime.trim().isEmpty())
      throw new Exception("Autor imePrezime ne sme biti prazno.");
    String n = imePrezime.trim();

    for (Autor a : autorRepo.findAll()) {
      if (a.getImePrezime() != null && a.getImePrezime().equalsIgnoreCase(n)) return a;
    }
    Autor novi = new Autor();
    novi.setImePrezime(n);
    return autorRepo.save(novi);
  }
    
}
