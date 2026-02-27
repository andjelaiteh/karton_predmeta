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
    private Long tipLiteratureId;
    private List<String> autori;

    public CreateLiteraturaStavkaDto() {
    }

    public CreateLiteraturaStavkaDto(String naslov, Integer godina, String izdavacNaziv, Long tipLiteratureId, List<String> autori) {
        this.naslov = naslov;
        this.godina = godina;
        this.izdavacNaziv = izdavacNaziv;
        this.tipLiteratureId = tipLiteratureId;
        this.autori = autori;
    }

    public List<String> getAutori() {
        return autori;
    }

    public void setAutori(List<String> autori) {
        this.autori = autori;
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

    public Long getTipLiteratureId() {
        return tipLiteratureId;
    }

    public void setTipLiteratureId(Long tipLiteratureId) {
        this.tipLiteratureId = tipLiteratureId;
    }

   
    
}
