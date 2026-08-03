/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.dto;

import java.util.List;

/**
 *
 * @author Andjela
 */
public class PredmetDetaljiDto {
    private Long id;
    private String naziv;
    private Integer espb;
    private String program;
    private String statusPredmeta;
    private List<NastavnikDto> nastavnici;
    private List<CasDto> casovi;
    private List<LiteraturaDto> literatura;
    private List<OcenjivanjeStavkaDto> ocene;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Integer getEspb() {
        return espb;
    }

    public void setEspb(Integer espb) {
        this.espb = espb;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getStatusPredmeta() {
        return statusPredmeta;
    }

    public void setStatusPredmeta(String statusPredmeta) {
        this.statusPredmeta = statusPredmeta;
    }

    public List<NastavnikDto> getNastavnici() {
        return nastavnici;
    }

    public void setNastavnici(List<NastavnikDto> nastavnici) {
        this.nastavnici = nastavnici;
    }

    public List<CasDto> getCasovi() {
        return casovi;
    }

    public void setCasovi(List<CasDto> casovi) {
        this.casovi = casovi;
    }

    public List<LiteraturaDto> getLiteratura() {
        return literatura;
    }

    public void setLiteratura(List<LiteraturaDto> literatura) {
        this.literatura = literatura;
    }

    public List<OcenjivanjeStavkaDto> getOcene() {
        return ocene;
    }

    public void setOcene(List<OcenjivanjeStavkaDto> ocene) {
        this.ocene = ocene;
    }

    
    
    public PredmetDetaljiDto() {
    }

    public PredmetDetaljiDto(Long id, String naziv, Integer espb, String program, String statusPredmeta, List<NastavnikDto> nastavnici, List<CasDto> casovi, List<LiteraturaDto> literatura, List<OcenjivanjeStavkaDto> ocene) {
        this.id = id;
        this.naziv = naziv;
        this.espb = espb;
        this.program = program;
        this.statusPredmeta = statusPredmeta;
        this.nastavnici = nastavnici;
        this.casovi = casovi;
        this.literatura = literatura;
        this.ocene = ocene;
    }
    
    

}
