/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.entity;
import jakarta.persistence.*;

/**
 *
 * @author Andjela
 */

@Entity
@Table(name = "ocenjivanje")
public class Ocenjivanje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ocenjivanjeid")
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "predmetid", nullable = false)
    private Predmet predmet;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "obavezaid", nullable = false)
    private IspitnaObaveza ispitnaObaveza;

    @Column(name = "obavezna", nullable = false)
    private Boolean obavezna;

    @Column(name = "poeni", nullable = false, precision = 5, scale = 2)
    private Integer poeni;

    public Ocenjivanje() {
    }

    public Ocenjivanje(Long id, Predmet predmet, IspitnaObaveza ispitnaObaveza, Boolean obavezna, Integer poeni) {
        this.id = id;
        this.predmet = predmet;
        this.ispitnaObaveza = ispitnaObaveza;
        this.obavezna = obavezna;
        this.poeni = poeni;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public IspitnaObaveza getIspitnaObaveza() {
        return ispitnaObaveza;
    }

    public void setIspitnaObaveza(IspitnaObaveza ispitnaObaveza) {
        this.ispitnaObaveza = ispitnaObaveza;
    }

    public Boolean getObavezna() {
        return obavezna;
    }

    public void setObavezna(Boolean obavezna) {
        this.obavezna = obavezna;
    }

    public Integer getPoeni() {
        return poeni;
    }

    public void setPoeni(Integer poeni) {
        this.poeni = poeni;
    }
    
    
}
