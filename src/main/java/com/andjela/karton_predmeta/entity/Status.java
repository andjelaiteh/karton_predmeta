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
@Table(name = "status")
public class Status {
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statusid")
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "predmetid", nullable = false)
    private Predmet predmet;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "programid", nullable = false)
    private StudijskiProgram program;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "tipstatusaid", nullable = false)
    private TipStatusa tipStatusa;

    public Status() {}

    public Status(Long id, Predmet predmet, StudijskiProgram program, TipStatusa tipStatusa) {
        this.id = id;
        this.predmet = predmet;
        this.program = program;
        this.tipStatusa = tipStatusa;
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

    public StudijskiProgram getProgram() {
        return program;
    }

    public void setProgram(StudijskiProgram program) {
        this.program = program;
    }

    public TipStatusa getTipStatusa() {
        return tipStatusa;
    }

    public void setTipStatusa(TipStatusa tipStatusa) {
        this.tipStatusa = tipStatusa;
    }
    
    
    
}
