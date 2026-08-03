/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.dto;

/**
 *
 * @author Andjela
 */
public class LiteraturaDto {
    private String naslov;
    private Integer godina;
    private String izdavac;
    private String tipLiterature;

    public LiteraturaDto() {
    }

    public LiteraturaDto(String naslov, Integer godina, String izdavac, String tipLiterature) {
        this.naslov = naslov;
        this.godina = godina;
        this.izdavac = izdavac;
        this.tipLiterature = tipLiterature;
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

    public String getIzdavac() {
        return izdavac;
    }

    public void setIzdavac(String izdavac) {
        this.izdavac = izdavac;
    }

    public String getTipLiterature() {
        return tipLiterature;
    }

    public void setTipLiterature(String tipLiterature) {
        this.tipLiterature = tipLiterature;
    }
    
    
    
}
