/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.service.impl;

import com.andjela.karton_predmeta.dto.CreateNastavaDto;
import com.andjela.karton_predmeta.dto.NastavaStavkaDto;
import com.andjela.karton_predmeta.entity.Nastava;
import com.andjela.karton_predmeta.entity.Predmet;
import com.andjela.karton_predmeta.entity.TipNastave;
import com.andjela.karton_predmeta.repository.NastavaRepository;
import com.andjela.karton_predmeta.repository.PredmetRepository;
import com.andjela.karton_predmeta.repository.TipNastaveRepository;
import com.andjela.karton_predmeta.service.NastavaService;
import jakarta.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andjela
 */
@Service
public class NastavaServiceImpl implements NastavaService{
    private final NastavaRepository nastavaRepo;
    private final PredmetRepository predmetRepo;
    private final TipNastaveRepository tipNastaveRepo;

    public NastavaServiceImpl(NastavaRepository nastavaRepo, PredmetRepository predmetRepo, TipNastaveRepository tipNastaveRepo) {
        this.nastavaRepo = nastavaRepo;
        this.predmetRepo = predmetRepo;
        this.tipNastaveRepo = tipNastaveRepo;
    }

  
    
    @Override
    @Transactional
    public void create(CreateNastavaDto dto) throws Exception {
         if (dto == null) throw new Exception("Request body je obavezan.");
        if (dto.getPredmetId() == null) throw new Exception("predmetId je obavezan.");
        if (dto.getStavke() == null) throw new Exception("stavke su obavezne.");

        // saljes i nule -> mora tacno 5 stavki
        if (dto.getStavke().size() != 5) {
            throw new Exception("Moras poslati tacno 5 stavki (za svih 5 tipova nastave).");
        }

        // validacija: nema duplih tipova, brojCasova >= 0, zbir=4
        int sum = 0;
        Set<Long> seenTipIds = new HashSet<>();

        for (NastavaStavkaDto s : dto.getStavke()) {
            
            if (s == null) throw new Exception("stavke ne smeju sadrzati null.");
            if (s.getTipNastaveId() == null) throw new Exception("tipNastaveId je obavezan.");
            if (s.getBrojCasova() == null) throw new Exception("brojCasova je obavezan.");
            if (s.getBrojCasova() < 0) throw new Exception("brojCasova ne sme biti negativan.");

            sum += s.getBrojCasova();
        }

        if (sum != 4) {
            throw new Exception("Zbir casova mora biti tacno 4");
        }

        // predmet mora postojati (ako ga kreiras ranije u istoj transakciji, bice tu)
        Predmet predmet = predmetRepo.findById(dto.getPredmetId())
                .orElseThrow(() -> new Exception("Predmet ne postoji."));

        // INSERT 5 redova
        for (NastavaStavkaDto s : dto.getStavke()) {

            TipNastave tip = tipNastaveRepo.findById(s.getTipNastaveId())
                    .orElseThrow(() -> new Exception("Tip nastave ne postoji: " + s.getTipNastaveId()));

            Nastava n = new Nastava();
            n.setPredmet(predmet);
            n.setTipNastave(tip);
            n.setBrojCasova(s.getBrojCasova());

            nastavaRepo.save(n);
        }
    }
    
}
