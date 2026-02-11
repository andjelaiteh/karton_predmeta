/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.dto;

/**
 *
 * @author Andjela
 */
public class NastavaStavkaDto {
    private Long tipNastaveId;
    private Integer brojCasova;

    public NastavaStavkaDto() {
    }

    public NastavaStavkaDto(Long tipNastaveId, Integer brojCasova) {
        this.tipNastaveId = tipNastaveId;
        this.brojCasova = brojCasova;
    }

    public Long getTipNastaveId() {
        return tipNastaveId;
    }

    public void setTipNastaveId(Long tipNastaveId) {
        this.tipNastaveId = tipNastaveId;
    }

    public Integer getBrojCasova() {
        return brojCasova;
    }

    public void setBrojCasova(Integer brojCasova) {
        this.brojCasova = brojCasova;
    }
    
    
}
