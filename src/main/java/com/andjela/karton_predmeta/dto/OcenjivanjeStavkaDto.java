/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.dto;

/**
 *
 * @author Andjela
 */
public class OcenjivanjeStavkaDto {
   private String nazivObaveze;
   private Integer poeni;
   private Boolean obavezna;

    public OcenjivanjeStavkaDto() {
    }

    public OcenjivanjeStavkaDto(String nazivObaveze, Integer poeni, Boolean obavezna) {
        this.nazivObaveze = nazivObaveze;
        this.poeni = poeni;
        this.obavezna = obavezna;
    }

    public String getNazivObaveze() {
        return nazivObaveze;
    }

    public void setNazivObaveze(String nazivObaveze) {
        this.nazivObaveze = nazivObaveze;
    }

    public Integer getPoeni() {
        return poeni;
    }

    public void setPoeni(Integer poeni) {
        this.poeni = poeni;
    }

    public Boolean getObavezna() {
        return obavezna;
    }

    public void setObavezna(Boolean obavezna) {
        this.obavezna = obavezna;
    }
   
   
}
