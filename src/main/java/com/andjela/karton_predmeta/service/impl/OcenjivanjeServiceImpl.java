/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.service.impl;

import com.andjela.karton_predmeta.dto.CreateOcenjivanjeDto;
import com.andjela.karton_predmeta.dto.OcenjivanjeStavkaDto;
import com.andjela.karton_predmeta.entity.IspitnaObaveza;
import com.andjela.karton_predmeta.entity.Ocenjivanje;
import com.andjela.karton_predmeta.entity.Predmet;
import com.andjela.karton_predmeta.entity.TipObaveze;
import com.andjela.karton_predmeta.repository.IspitnaObavezaRepository;
import com.andjela.karton_predmeta.repository.OcenjivanjeRepository;
import com.andjela.karton_predmeta.repository.PredmetRepository;
import com.andjela.karton_predmeta.repository.TipObavezeRepository;
import com.andjela.karton_predmeta.service.OcenjivanjeService;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andjela
 */
@Service
public class OcenjivanjeServiceImpl implements OcenjivanjeService{
    private final OcenjivanjeRepository ocenjivanjeRepo;
    private final PredmetRepository predmetRepo;
    private final TipObavezeRepository tipRepo;
    private final IspitnaObavezaRepository obavezaRepo;

    public OcenjivanjeServiceImpl(OcenjivanjeRepository ocenjivanjeRepo,
                                  PredmetRepository predmetRepo,
                                  TipObavezeRepository tipRepo,
                                  IspitnaObavezaRepository obavezaRepo) {
        this.ocenjivanjeRepo = ocenjivanjeRepo;
        this.predmetRepo = predmetRepo;
        this.tipRepo = tipRepo;
        this.obavezaRepo = obavezaRepo;
    }

    @Override
    @Transactional
    public void create(CreateOcenjivanjeDto dto) throws Exception {

        if (dto == null) throw new Exception("Request body je obavezan.");
        if (dto.getPredmetId() == null) throw new Exception("predmetId je obavezan.");

        List<OcenjivanjeStavkaDto> predispitne = dto.getPredispitne() == null ? List.of() : dto.getPredispitne();
        List<OcenjivanjeStavkaDto> ispitne = dto.getIspitne() == null ? List.of() : dto.getIspitne();

        if (predispitne.isEmpty() && ispitne.isEmpty()) {
            throw new Exception("Morate uneti bar jednu stavku ocenjivanja.");
        }

        int sum = 0;
        for (OcenjivanjeStavkaDto s : predispitne) sum += validateStavka(s);
        for (OcenjivanjeStavkaDto s : ispitne) sum += validateStavka(s);

        if (sum != 100) {
            throw new Exception("Ukupan zbir poena mora biti 100.");
        }

        Predmet predmet = predmetRepo.findById(dto.getPredmetId())
                .orElseThrow(() -> new Exception("Predmet ne postoji."));

        TipObaveze tipPred = findOrCreateTip("PREDISPITNA");
        TipObaveze tipIsp = findOrCreateTip("ISPITNA");

        saveGroup(predispitne, predmet, tipPred);
        saveGroup(ispitne, predmet, tipIsp);
    }

    private int validateStavka(OcenjivanjeStavkaDto s) throws Exception {
        if (s == null) throw new Exception("Stavka ne sme biti null.");
        if (s.getNazivObaveze() == null || s.getNazivObaveze().trim().isEmpty())
            throw new Exception("Naziv obaveze je obavezan.");
        if (s.getPoeni() == null) throw new Exception("Poeni su obavezni.");
        if (s.getPoeni() < 0) throw new Exception("Poeni ne smeju biti negativni.");
        if (s.getObavezna() == null) throw new Exception("Obavezna mora biti true/false.");
        return s.getPoeni();
    }

    private TipObaveze findOrCreateTip(String naziv) {
        for (TipObaveze t : tipRepo.findAll()) {
            if (t.getNaziv() != null && t.getNaziv().equalsIgnoreCase(naziv)) {
                return t;
            }
        }
        TipObaveze novi = new TipObaveze();
        novi.setNaziv(naziv);
        return tipRepo.save(novi);
    }

    private IspitnaObaveza findOrCreateObaveza(String nazivObaveze, TipObaveze tip) {
        String n = nazivObaveze.trim();

        for (IspitnaObaveza o : obavezaRepo.findAll()) {
            boolean istiNaziv = o.getNazivObaveze() != null && o.getNazivObaveze().equalsIgnoreCase(n);
            boolean istiTip = o.getTipObaveze() != null
                    && o.getTipObaveze().getId() != null
                    && tip.getId() != null
                    && o.getTipObaveze().getId().equals(tip.getId());

            if (istiNaziv && istiTip) return o;
        }

        IspitnaObaveza nova = new IspitnaObaveza();
        nova.setNazivObaveze(n);
        nova.setTipObaveze(tip);
        return obavezaRepo.save(nova);
    }

    private void saveGroup(List<OcenjivanjeStavkaDto> list, Predmet predmet, TipObaveze tip) {
        for (OcenjivanjeStavkaDto s : list) {

            IspitnaObaveza obaveza = findOrCreateObaveza(s.getNazivObaveze(), tip);

            Ocenjivanje o = new Ocenjivanje();
            o.setPredmet(predmet);
            o.setIspitnaObaveza(obaveza);
            o.setPoeni(s.getPoeni());
            o.setObavezna(s.getObavezna());

            ocenjivanjeRepo.save(o);
        }
    }
}
