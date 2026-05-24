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
import exception.NotFoundException;
import exception.ValidationException;
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
    public void create(CreateNastavaDto dto){
        if (dto == null) throw new ValidationException("Request body je obavezan.");
        if (dto.getPredmetId() == null) throw new ValidationException ("predmetId je obavezan.");
        if (dto.getStavke() == null) throw new ValidationException("stavke su obavezne.");

        // saljes i nule -> mora tacno 5 stavki
        if (dto.getStavke().size() != 5) {
            throw new ValidationException("Moras poslati tacno 5 stavki (za svih 5 tipova nastave).");
        }

        // validacija: nema duplih tipova, brojCasova >= 0, zbir=4
        int sum = 0;
        

        for (NastavaStavkaDto s : dto.getStavke()) {
            
            if (s == null) throw new ValidationException("stavke ne smeju sadrzati null.");
            if (s.getTipNastaveId() == null)throw new ValidationException("tipNastaveId je obavezan.");
            if (s.getBrojCasova() == null) throw new ValidationException("brojCasova je obavezan.");
            if (s.getBrojCasova() < 0) throw new ValidationException("brojCasova ne sme biti negativan.");

            sum += s.getBrojCasova();
        }

        if (sum != 4) {
            throw new ValidationException("Zbir casova mora biti tacno 4");
        }

        // predmet mora postojati (ako ga kreiras ranije u istoj transakciji, bice tu)
        Predmet predmet = predmetRepo.findById(dto.getPredmetId())
                .orElseThrow(() -> new NotFoundException("Predmet ne postoji."));

        // INSERT 5 redova
        for (NastavaStavkaDto s : dto.getStavke()) {

            TipNastave tip = tipNastaveRepo.findById(s.getTipNastaveId())
                    .orElseThrow(() -> new NotFoundException("Tip nastave ne postoji: " + s.getTipNastaveId()));

            Nastava n = new Nastava();
            n.setPredmet(predmet);
            n.setTipNastave(tip);
            n.setBrojCasova(s.getBrojCasova());

            nastavaRepo.save(n);
        }
    }
    
}
