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
@Table(name="literatura")
public class Literatura {
    
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="literaturaid")
  private Long id;

  @Column(name="naslov", nullable=false, length=255)
  private String naslov;

  @Column(name="godina", nullable=false)
  private Integer godina;

  @ManyToOne(fetch = FetchType.LAZY, optional=false)
  @JoinColumn(name="izdavacid", nullable=false)
  private Izdavac izdavac;

    public Literatura() {
    }

    public Literatura(Long id, String naslov, Integer godina, Izdavac izdavac) {
        this.id = id;
        this.naslov = naslov;
        this.godina = godina;
        this.izdavac = izdavac;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public Integer getGodina() {
        return godina;
    }

    public void setGodina(Integer godina) {
        this.godina = godina;
    }

    public Izdavac getIzdavac() {
        return izdavac;
    }

    public void setIzdavac(Izdavac izdavac) {
        this.izdavac = izdavac;
    }
  
  
}
