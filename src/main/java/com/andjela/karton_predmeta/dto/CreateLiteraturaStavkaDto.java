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
public class CreateLiteraturaStavkaDto {
    
    private String naslov;
    private Integer godina;
    private String izdavacNaziv;
    private List<String> autoriImePrezime; 
    private Boolean obavezna;

    public CreateLiteraturaStavkaDto() {
    }

    public CreateLiteraturaStavkaDto(String naslov, Integer godina, String izdavacNaziv, List<String> autoriImePrezime, Boolean obavezna) {
        this.naslov = naslov;
        this.godina = godina;
        this.izdavacNaziv = izdavacNaziv;
        this.autoriImePrezime = autoriImePrezime;
        this.obavezna = obavezna;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public Integer getGodina() {
        return godina;
    }

    public void setGodina(Integer godina) {
        this.godina = godina;
    }

    public String getIzdavacNaziv() {
        return izdavacNaziv;
    }

    public void setIzdavacNaziv(String izdavacNaziv) {
        this.izdavacNaziv = izdavacNaziv;
    }

    public List<String> getAutoriImePrezime() {
        return autoriImePrezime;
    }

    public void setAutoriImePrezime(List<String> autoriImePrezime) {
        this.autoriImePrezime = autoriImePrezime;
    }

    public Boolean getObavezna() {
        return obavezna;
    }

    public void setObavezna(Boolean obavezna) {
        this.obavezna = obavezna;
    }
    
    
}
