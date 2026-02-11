/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.entity;


import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Andjela
 */
@Entity
@Table(name = "predmet")
public class Predmet {

  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "predmetid")   // <-- kao u bazi
    private Long id;

    @Column(name = "naziv_predmeta", nullable = false, length = 255) // <-- kao u bazi
    private String naziv;

    @Column(name = "espb", nullable = false)
    private Integer espb;

    public Predmet() {}

    public Predmet(Long id, String naziv, Integer espb) {
        this.id = id;
        this.naziv = naziv;
        this.espb = espb;
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

    public Integer getEspb() {
        return espb;
    }

    public void setEspb(Integer espb) {
        this.espb = espb;
    }

   
    
    
}