/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.service.impl;

import com.andjela.karton_predmeta.dto.CasDto;
import com.andjela.karton_predmeta.dto.LiteraturaDto;
import com.andjela.karton_predmeta.dto.NastavnikDto;
import com.andjela.karton_predmeta.dto.OcenjivanjeStavkaDto;
import com.andjela.karton_predmeta.dto.PredmetDetaljiDto;
import com.andjela.karton_predmeta.dto.PredmetDto;
import com.andjela.karton_predmeta.entity.Predmet;
import com.andjela.karton_predmeta.entity.Status;
import com.andjela.karton_predmeta.entity.StudijskiProgram;
import com.andjela.karton_predmeta.entity.TipStatusa;
import com.andjela.karton_predmeta.mapper.impl.PredmetDtoEntityMapper;
import com.andjela.karton_predmeta.repository.AngazovanjeRepository;
import com.andjela.karton_predmeta.repository.NastavaRepository;
import com.andjela.karton_predmeta.repository.OcenjivanjeRepository;
import com.andjela.karton_predmeta.repository.PredmetLiteraturaRepository;
import com.andjela.karton_predmeta.repository.PredmetRepository;
import com.andjela.karton_predmeta.repository.StatusRepository;
import com.andjela.karton_predmeta.repository.StudijskiProgramRepository;
import com.andjela.karton_predmeta.repository.TipStatusaRepository;
import com.andjela.karton_predmeta.service.PredmetService;
import exception.ValidationException;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andjela
 */
@Service
public class PredmetServiceImpl implements PredmetService{

    private final PredmetRepository predmetRepository;
    private final PredmetDtoEntityMapper predmetMapper;
    private final StatusRepository statusRepository;
    private final AngazovanjeRepository angazovanjeRepository;
    private final NastavaRepository nastavaRepository;
    private final PredmetLiteraturaRepository predmetLiteraturaRepository;
    private final OcenjivanjeRepository ocenjivanjeRepository;
    private final StudijskiProgramRepository studijskiProgramRepository;
    private final TipStatusaRepository tipStatusaRepository;

    public PredmetServiceImpl(PredmetRepository predmetRepository, PredmetDtoEntityMapper predmetMapper, StatusRepository statusRepository, AngazovanjeRepository angazovanjeRepository, NastavaRepository nastavaRepository, PredmetLiteraturaRepository predmetLiteraturaRepository, OcenjivanjeRepository ocenjivanjeRepository, StudijskiProgramRepository studijskiProgramRepository, TipStatusaRepository tipStatusaRepository) {
        this.predmetRepository = predmetRepository;
        this.predmetMapper = predmetMapper;
        this.statusRepository = statusRepository;
        this.angazovanjeRepository = angazovanjeRepository;
        this.nastavaRepository = nastavaRepository;
        this.predmetLiteraturaRepository = predmetLiteraturaRepository;
        this.ocenjivanjeRepository = ocenjivanjeRepository;
        this.studijskiProgramRepository = studijskiProgramRepository;
        this.tipStatusaRepository = tipStatusaRepository;
    }

    @Override
    public PredmetDto save(PredmetDto predmetDto) {
         if (predmetDto.getNaziv() == null || predmetDto.getNaziv().trim().isEmpty()) {
            throw new ValidationException("Naziv predmeta je obavezan.");
        }
        if (predmetDto.getEspb() == null || predmetDto.getEspb() <= 0) {
            throw new ValidationException("ESPB mora biti pozitivan broj.");
        }

        Predmet predmet = predmetMapper.toEntity(predmetDto);
        predmet = predmetRepository.save(predmet);
        return predmetMapper.toDto(predmet);}

    @Override
    public List<PredmetDto> findAll() {
         return predmetRepository.findAll()
            .stream()
            .map(predmetMapper::toDto)
            .toList();
    }

    @Override
    public PredmetDto findById(Long id) {
      Predmet predmet = predmetRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Predmet sa ID " + id + " ne postoji."));
        return predmetMapper.toDto(predmet);
    }

    @Override
    public PredmetDetaljiDto findDetaljiById(Long id) {
        Predmet predmet = predmetRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Predmet sa ID " + id + " ne postoji."));

        PredmetDetaljiDto dto = new PredmetDetaljiDto();
        dto.setId(predmet.getId());
        dto.setNaziv(predmet.getNaziv());
        dto.setEspb(predmet.getEspb());

        Status status = statusRepository.findByPredmet_Id(id);
        if (status != null) {
            dto.setProgram(status.getProgram().getNaziv());
            dto.setStatusPredmeta(status.getTipStatusa().getNaziv());
        }

        List<NastavnikDto> nastavnici = angazovanjeRepository.findByPredmet_Id(id)
                .stream()
                .map(a -> new NastavnikDto(
                        a.getNastavnik().getId(),
                        a.getNastavnik().getIme(),
                        a.getNastavnik().getPrezime(),
                        a.getNastavnik().getTipZvanja().getNaziv()
                ))
                .toList();
        dto.setNastavnici(nastavnici);

        List<CasDto> casovi = nastavaRepository.findByPredmet_Id(id)
                .stream()
                .map(n -> new CasDto(
                        n.getTipNastave().getNaziv(),
                        n.getBrojCasova()
                ))
                .toList();
        dto.setCasovi(casovi);

        List<LiteraturaDto> literatura = predmetLiteraturaRepository.findByPredmet_Id(id)
                .stream()
                .map(pl -> new LiteraturaDto(
                        pl.getLiteratura().getNaslov(),
                        pl.getLiteratura().getGodina(),
                        pl.getLiteratura().getIzdavac().getNaziv(),
                        pl.getTipLiterature().getNaziv()
                ))
                .toList();
        dto.setLiteratura(literatura);

        List<OcenjivanjeStavkaDto> ocene = ocenjivanjeRepository.findByPredmet_Id(id)
                .stream()
                .map(o -> new OcenjivanjeStavkaDto(
                        o.getIspitnaObaveza().getNazivObaveze(),
                        o.getPoeni(),
                        o.getObavezna()
                ))
                .toList();
        dto.setOcene(ocene);

        return dto;
    }

    @Override
    public void obrisiDetaljePredmeta(Long id) {
        angazovanjeRepository.deleteByPredmet_Id(id);
        nastavaRepository.deleteByPredmet_Id(id);
        ocenjivanjeRepository.deleteByPredmet_Id(id);
        predmetLiteraturaRepository.deleteByPredmet_Id(id);
    }

    @Override
    public void obrisiPredmet(Long id) {
        angazovanjeRepository.deleteByPredmet_Id(id);
        nastavaRepository.deleteByPredmet_Id(id);
        ocenjivanjeRepository.deleteByPredmet_Id(id);
        predmetLiteraturaRepository.deleteByPredmet_Id(id);
        statusRepository.deleteByPredmet_Id(id);
        predmetRepository.deleteById(id);
    }

    @Override
    public void izmeniOsnovnePodatke(Long id, String naziv, Integer espb, Long programId, Long tipStatusaId) {
        if (naziv == null || naziv.trim().isEmpty())
            throw new ValidationException("Naziv predmeta je obavezan.");
        if (espb == null || espb <= 0)
            throw new ValidationException("ESPB mora biti pozitivan broj.");

        Predmet predmet = predmetRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Predmet ne postoji."));
        predmet.setNaziv(naziv.trim());
        predmet.setEspb(espb);
        predmetRepository.save(predmet);

        Status status = statusRepository.findByPredmet_Id(id);
        if (status != null) {
            StudijskiProgram program = studijskiProgramRepository.findById(programId)
                    .orElseThrow(() -> new ValidationException("Program ne postoji."));
            TipStatusa tip = tipStatusaRepository.findById(tipStatusaId)
                    .orElseThrow(() -> new ValidationException("Tip statusa ne postoji."));
            status.setProgram(program);
            status.setTipStatusa(tip);
            statusRepository.save(status);
        }
        }
 
}
