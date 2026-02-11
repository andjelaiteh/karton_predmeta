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
public class DodajNastavnikeNaPredmetDto {
    private Long predmetId;
    private List<Long> nastavnikIds;

    public DodajNastavnikeNaPredmetDto() {
    }

    public DodajNastavnikeNaPredmetDto(Long predmetId, List<Long> nastavnikIds) {
        this.predmetId = predmetId;
        this.nastavnikIds = nastavnikIds;
    }

    public Long getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(Long predmetId) {
        this.predmetId = predmetId;
    }

    public List<Long> getNastavnikIds() {
        return nastavnikIds;
    }

    public void setNastavnikIds(List<Long> nastavnikIds) {
        this.nastavnikIds = nastavnikIds;
    }
    
    
}
