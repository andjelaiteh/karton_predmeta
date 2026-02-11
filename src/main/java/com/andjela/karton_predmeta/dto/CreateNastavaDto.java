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
public class CreateNastavaDto {
    private Long predmetId;
    private List<NastavaStavkaDto> stavke;

    public CreateNastavaDto() {
    }

    public CreateNastavaDto(Long predmetId, List<NastavaStavkaDto> stavke) {
        this.predmetId = predmetId;
        this.stavke = stavke;
    }

    public Long getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(Long predmetId) {
        this.predmetId = predmetId;
    }

    public List<NastavaStavkaDto> getStavke() {
        return stavke;
    }

    public void setStavke(List<NastavaStavkaDto> stavke) {
        this.stavke = stavke;
    }

    
}
