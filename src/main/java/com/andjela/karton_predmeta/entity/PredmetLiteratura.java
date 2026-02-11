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
@Table(name="predmet_literatura")
public class PredmetLiteratura {
    
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="predmet_literaturaid")
   private Long id;

   @ManyToOne(fetch = FetchType.LAZY, optional=false)
   @JoinColumn(name="predmetid", nullable=false)
   private Predmet predmet;

   @ManyToOne(fetch = FetchType.LAZY, optional=false)
   @JoinColumn(name="literaturaid", nullable=false)
   private Literatura literatura;

   @Column(name="obavezna", nullable=false)
   private Boolean obavezna;

    public PredmetLiteratura() {
    }

    public PredmetLiteratura(Long id, Predmet predmet, Literatura literatura, Boolean obavezna) {
        this.id = id;
        this.predmet = predmet;
        this.literatura = literatura;
        this.obavezna = obavezna;
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

    public Literatura getLiteratura() {
        return literatura;
    }

    public void setLiteratura(Literatura literatura) {
        this.literatura = literatura;
    }

    public Boolean getObavezna() {
        return obavezna;
    }

    public void setObavezna(Boolean obavezna) {
        this.obavezna = obavezna;
    }
  
    
  
}
