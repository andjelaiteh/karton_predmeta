/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.service.impl;

import com.andjela.karton_predmeta.dto.CreateStatusDto;
import com.andjela.karton_predmeta.entity.Predmet;
import com.andjela.karton_predmeta.entity.Status;
import com.andjela.karton_predmeta.entity.StudijskiProgram;
import com.andjela.karton_predmeta.entity.TipStatusa;
import com.andjela.karton_predmeta.repository.PredmetRepository;
import com.andjela.karton_predmeta.repository.StatusRepository;
import com.andjela.karton_predmeta.repository.StudijskiProgramRepository;
import com.andjela.karton_predmeta.repository.TipStatusaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.andjela.karton_predmeta.service.StatusService;

/**
 *
 * @author Andjela
 */
@Service
public class StatusServiceImpl implements StatusService{

    private final PredmetRepository predmetRepo;
    private final StudijskiProgramRepository programRepo;
    private final TipStatusaRepository tipStatusaRepo;
    private final StatusRepository statusRepo;

    public StatusServiceImpl(PredmetRepository predmetRepo, StudijskiProgramRepository programRepo, TipStatusaRepository tipStatusaRepo, StatusRepository statusRepo) {
        this.predmetRepo = predmetRepo;
        this.programRepo = programRepo;
        this.tipStatusaRepo = tipStatusaRepo;
        this.statusRepo = statusRepo;
    }

    
    @Override
    @Transactional
    public Predmet create(CreateStatusDto csd) throws Exception {
        if (csd.getNaziv() == null || csd.getNaziv().trim().isEmpty())
            throw new Exception("Naziv predmeta je obavezan.");
        if (csd.getEspb() == null || csd.getEspb() <= 0)
            throw new Exception("ESPB mora biti pozitivan broj.");
        if (csd.getProgramId() == null)
            throw new Exception("Program je obavezan.");
        if (csd.getTipStatusaId() == null)
            throw new Exception("Tip statusa je obavezan.");

        StudijskiProgram program = programRepo.findById(csd.getProgramId())
                .orElseThrow(() -> new Exception("Program ne postoji."));

        TipStatusa tipStatusa = tipStatusaRepo.findById(csd.getTipStatusaId())
                .orElseThrow(() -> new Exception("Tip statusa ne postoji."));

        Predmet predmet = new Predmet(null, csd.getNaziv().trim(), csd.getEspb());
        predmet = predmetRepo.save(predmet);
        
        Status status = new Status();
        status.setPredmet(predmet);
        status.setProgram(program);
        status.setTipStatusa(tipStatusa);
        
        statusRepo.save(status);
        return predmet;
    }
    
}
