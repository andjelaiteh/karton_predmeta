/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.service.impl;

import com.andjela.karton_predmeta.dto.NastavnikDto;
import com.andjela.karton_predmeta.entity.Angazovanje;
import com.andjela.karton_predmeta.entity.Nastavnik;
import com.andjela.karton_predmeta.entity.Predmet;
import com.andjela.karton_predmeta.repository.AngazovanjeRepository;
import com.andjela.karton_predmeta.repository.NastavnikRepository;
import com.andjela.karton_predmeta.repository.PredmetRepository;
import com.andjela.karton_predmeta.service.NastavnikAngazovanjaService;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andjela
 */
@Service
@Transactional
public class NastavnikAngazovanjeServiceImpl implements NastavnikAngazovanjaService{
     private final NastavnikRepository nastavnikRepo;
    private final PredmetRepository predmetRepo;
    private final AngazovanjeRepository angazovanjeRepo;

    public NastavnikAngazovanjeServiceImpl(NastavnikRepository nastavnikRepo, PredmetRepository predmetRepo, AngazovanjeRepository angazovanjeRepo) {
        this.nastavnikRepo = nastavnikRepo;
        this.predmetRepo = predmetRepo;
        this.angazovanjeRepo = angazovanjeRepo;
    }

    @Override
    public List<NastavnikDto> getAllNastavnici() {
        List<NastavnikDto> result = new ArrayList<>();
        for (Nastavnik n : nastavnikRepo.findAll()) {
            result.add(new NastavnikDto(
                    n.getId(),
                    n.getIme(),
                    n.getPrezime(),
                    n.getTipZvanja().getNaziv()
            ));
        }
        return result;}

    @Override
    public void dodajNastavnike(Long predmetId, List<Long> nastavnikIds) throws Exception {
         Predmet predmet = predmetRepo.findById(predmetId)
                .orElseThrow(() -> new Exception("Predmet ne postoji"));

        for (Long nastavnikId : nastavnikIds) {
            Nastavnik nastavnik = nastavnikRepo.findById(nastavnikId)
                    .orElseThrow(() -> new Exception("Nastavnik ne postoji"));

            Angazovanje a = new Angazovanje();
            a.setPredmet(predmet);
            a.setNastavnik(nastavnik);
            angazovanjeRepo.save(a);
        }
    } 


}

   

   

