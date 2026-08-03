/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.dto;

/**
 *
 * @author Andjela
 */
public class CasDto {
    private String tipNastave;
    private Integer brojCasova;

    public CasDto() {
    }

    public CasDto(String tipNastave, Integer brojCasova) {
        this.tipNastave = tipNastave;
        this.brojCasova = brojCasova;
    }

    public String getTipNastave() {
        return tipNastave;
    }

    public void setTipNastave(String tipNastave) {
        this.tipNastave = tipNastave;
    }

    public Integer getBrojCasova() {
        return brojCasova;
    }

    public void setBrojCasova(Integer brojCasova) {
        this.brojCasova = brojCasova;
    }
    
    
    
}
