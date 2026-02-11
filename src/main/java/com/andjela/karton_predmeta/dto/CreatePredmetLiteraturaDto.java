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
public class CreatePredmetLiteraturaDto {
    private Long predmetId;
    private List<CreateLiteraturaStavkaDto> stavke;

    public Long getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(Long predmetId) {
        this.predmetId = predmetId;
    }

    public List<CreateLiteraturaStavkaDto> getStavke() {
        return stavke;
    }

    public void setStavke(List<CreateLiteraturaStavkaDto> stavke) {
        this.stavke = stavke;
    }

    
    
    
}
