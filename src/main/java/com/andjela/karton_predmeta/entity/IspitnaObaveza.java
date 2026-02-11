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
@Table(name = "ispitna_obaveza")
public class IspitnaObaveza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "obavezaid")
    private Long id;

    @Column(name = "naziv_obaveze", nullable = false, length = 100)
    private String nazivObaveze;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "tipobavezeid", nullable = false)
    private TipObaveze tipObaveze;

    public IspitnaObaveza() {
    }

    public IspitnaObaveza(Long id, String nazivObaveze, TipObaveze tipObaveze) {
        this.id = id;
        this.nazivObaveze = nazivObaveze;
        this.tipObaveze = tipObaveze;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazivObaveze() {
        return nazivObaveze;
    }

    public void setNazivObaveze(String nazivObaveze) {
        this.nazivObaveze = nazivObaveze;
    }

    public TipObaveze getTipObaveze() {
        return tipObaveze;
    }

    public void setTipObaveze(TipObaveze tipObaveze) {
        this.tipObaveze = tipObaveze;
    }
    
}
