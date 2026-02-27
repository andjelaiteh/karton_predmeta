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

   @ManyToOne(optional = false)
   @JoinColumn(name = "tip_literature_id")
   private TipLiterature tipLiterature;


    public PredmetLiteratura() {
    }

    public PredmetLiteratura(Long id, Predmet predmet, Literatura literatura, TipLiterature tipLiterature) {
        this.id = id;
        this.predmet = predmet;
        this.literatura = literatura;
        this.tipLiterature = tipLiterature;
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

    public TipLiterature getTipLiterature() {
        return tipLiterature;
    }

    public void setTipLiterature(TipLiterature tipLiterature) {
        this.tipLiterature = tipLiterature;
    }

   
    
  
}
