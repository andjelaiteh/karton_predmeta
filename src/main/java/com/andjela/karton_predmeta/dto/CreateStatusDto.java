/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.dto;

/**
 *
 * @author Andjela
 */
public class CreateStatusDto {
    private String naziv;
    private Integer espb;
    private Long programId;
    private Long tipStatusaId;

    public CreateStatusDto() {
    }

    public CreateStatusDto(String naziv, Integer espb, Long programId, Long tipStatusaId) {
        this.naziv = naziv;
        this.espb = espb;
        this.programId = programId;
        this.tipStatusaId = tipStatusaId;
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

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public Long getTipStatusaId() {
        return tipStatusaId;
    }

    public void setTipStatusaId(Long tipStatusaId) {
        this.tipStatusaId = tipStatusaId;
    }

    
}
