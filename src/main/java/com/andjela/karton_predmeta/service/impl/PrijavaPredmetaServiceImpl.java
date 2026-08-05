/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.service.impl;

import com.andjela.karton_predmeta.entity.Korisnik;
import com.andjela.karton_predmeta.entity.Predmet;
import com.andjela.karton_predmeta.entity.PrijavaPredmeta;
import com.andjela.karton_predmeta.entity.Status;
import com.andjela.karton_predmeta.repository.KorisnikRepository;
import com.andjela.karton_predmeta.repository.PredmetRepository;
import com.andjela.karton_predmeta.repository.PrijavaPredmetaRepository;
import com.andjela.karton_predmeta.repository.StatusRepository;
import com.andjela.karton_predmeta.service.PrijavaPredmetaService;
import io.getunleash.Unleash;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andjela
 */

@Service
public class PrijavaPredmetaServiceImpl implements PrijavaPredmetaService{
    
    @Autowired
    Unleash unleash;

    private final KorisnikRepository korisnikRepository;
    private final StatusRepository statusRepository;
    private final PredmetRepository predmetRepository;
    private final PrijavaPredmetaRepository prijavaPredmetaRepository;

    public PrijavaPredmetaServiceImpl(KorisnikRepository korisnikRepository, StatusRepository statusRepository, PredmetRepository predmetRepository, PrijavaPredmetaRepository prijavaPredmetaRepository) {
        this.korisnikRepository = korisnikRepository;
        this.statusRepository = statusRepository;
        this.predmetRepository = predmetRepository;
        this.prijavaPredmetaRepository = prijavaPredmetaRepository;
    }
    
    
    
    @Override
    public Map<String, Object> podaciKorisnika() {
        Korisnik k = trenutniKorisnik();
        Map<String, Object> m = new HashMap<>();
        m.put("ime", k.getIme());
        m.put("prezime", k.getPrezime());
        m.put("brojIndeksa", k.getBrojIndeksa());
        if (k.getProgram() != null) {
            m.put("programId", k.getProgram().getId());
            m.put("programNaziv", k.getProgram().getNaziv());
        } else {
            m.put("programId", null);
            m.put("programNaziv", null);
        }
        return m;
    }

    @Override
    public List<Map<String, Object>> obavezniPredmeti() {
        return predmetiPoTipu(1L);
    }

    @Override
    public List<Map<String, Object>> izborniPredmeti() {
        return predmetiPoTipu(2L);
    }

    private List<Map<String, Object>> predmetiPoTipu(Long tipStatusaId) {
        Korisnik k = trenutniKorisnik();
        List<Map<String, Object>> rezultat = new ArrayList<>();
        if (k.getProgram() == null) return rezultat;

        List<Status> statusi = statusRepository.findByProgram_IdAndTipStatusa_Id(k.getProgram().getId(), tipStatusaId);
        for (Status s : statusi) {
            Map<String, Object> m = new HashMap<>();
            m.put("id", s.getPredmet().getId());
            m.put("naziv", s.getPredmet().getNaziv());
            m.put("espb", s.getPredmet().getEspb());
            rezultat.add(m);
        }
        return rezultat;
    }

    @Override
    @Transactional
    public void sacuvajPrijavu(List<Long> izborniIds) {
        
        if (!unleash.isEnabled("registration-open"))
            throw new RuntimeException("Prijave predmeta su trenutno zatvorene.");
        
        Korisnik k = trenutniKorisnik();

        if (k.getProgram() == null)
            throw new RuntimeException("Nemaš dodeljen smer, ne možeš prijaviti predmete.");

        if (prijavaPredmetaRepository.existsByKorisnik_Id(k.getId()))
            throw new RuntimeException("Već si prijavio predmete.");

        if (izborniIds == null || izborniIds.size() != 3)
            throw new RuntimeException("Moraš izabrati tačno 3 izborna predmeta.");
        if (izborniIds.stream().distinct().count() != 3)
            throw new RuntimeException("Izborni predmeti moraju biti različiti.");
  
        List<Long> sviIds = new ArrayList<>();

        List<Status> obavezni = statusRepository.findByProgram_IdAndTipStatusa_Id(k.getProgram().getId(), 1L);
        for (Status s : obavezni) {
            sviIds.add(s.getPredmet().getId());
        }

        sviIds.addAll(izborniIds);

        for (Long predmetId : sviIds) {
            Predmet predmet = predmetRepository.findById(predmetId)
                    .orElseThrow(() -> new RuntimeException("Predmet ne postoji: " + predmetId));

            PrijavaPredmeta pp = new PrijavaPredmeta();
            pp.setKorisnik(k);
            pp.setPredmet(predmet);
            pp.setDatumPrijave(LocalDateTime.now());
            prijavaPredmetaRepository.save(pp);
        }
    }

    private Korisnik trenutniKorisnik() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return korisnikRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen."));
    }
    
}
