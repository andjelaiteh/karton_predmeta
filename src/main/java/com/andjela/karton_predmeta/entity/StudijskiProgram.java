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
@Table(name = "studijski_program")
public class StudijskiProgram {

    
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "programid") // kao u bazi
    private Long id;

    @Column(name = "naziv_programa", nullable = false, length = 200) // kao u bazi
    private String naziv;

    public StudijskiProgram() {}

    public StudijskiProgram(Long id, String naziv) {
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