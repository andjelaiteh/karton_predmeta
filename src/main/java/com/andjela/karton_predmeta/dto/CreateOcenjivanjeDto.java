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
public class CreateOcenjivanjeDto {
    private Long predmetId;
    private List<OcenjivanjeStavkaDto> predispitne;
    private List<OcenjivanjeStavkaDto> ispitne;

    public CreateOcenjivanjeDto() {
    }

    public CreateOcenjivanjeDto(Long predmetId, List<OcenjivanjeStavkaDto> predispitne, List<OcenjivanjeStavkaDto> ispitne) {
        this.predmetId = predmetId;
        this.predispitne = predispitne;
        this.ispitne = ispitne;
    }

    public Long getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(Long predmetId) {
        this.predmetId = predmetId;
    }

    public List<OcenjivanjeStavkaDto> getPredispitne() {
        return predispitne;
    }

    public void setPredispitne(List<OcenjivanjeStavkaDto> predispitne) {
        this.predispitne = predispitne;
    }

    public List<OcenjivanjeStavkaDto> getIspitne() {
        return ispitne;
    }

    public void setIspitne(List<OcenjivanjeStavkaDto> ispitne) {
        this.ispitne = ispitne;
    }
    
    
}
