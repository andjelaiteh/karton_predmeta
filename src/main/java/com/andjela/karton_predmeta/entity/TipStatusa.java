/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

/**
 *
 * @author Andjela
 */
@Entity
@Table(name = "tip_statusa")
public class TipStatusa {
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipstatusaid")
    private Long id;

    @Column(name = "naziv", nullable = false, unique = true, length = 50)
    private String naziv; 

    public TipStatusa() {
    }

    public TipStatusa(Long id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    
}
