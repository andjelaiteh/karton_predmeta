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
@Table(name = "tip_zvanja")
public class TipZvanja {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipzvanjaid")
    private Integer id;

    @Column(name = "naziv", nullable = false)
    private String naziv;

    public TipZvanja() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer tipzvanjaid) {
        this.id = tipzvanjaid;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
} 