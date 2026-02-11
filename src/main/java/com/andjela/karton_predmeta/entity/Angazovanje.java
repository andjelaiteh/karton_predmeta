/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
/**
 *
 * @author Andjela
 */
@Entity
@Table(name = "angazovanje")
public class Angazovanje {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "angazovanjeid")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nastavnikid", nullable = false)
    private Nastavnik nastavnik;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "predmetid", nullable = false)
    private Predmet predmet;

    public Angazovanje() {
    }

    public Angazovanje(Long id, Nastavnik nastavnik, Predmet predmet) {
        this.id = id;
        this.nastavnik = nastavnik;
        this.predmet = predmet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Nastavnik getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(Nastavnik nastavnik) {
        this.nastavnik = nastavnik;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }
    
    
    

}
