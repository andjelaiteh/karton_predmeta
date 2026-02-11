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
@Table(name="autor_literatura")
public class AutorLiteratura {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="autor_literaturaid")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional=false)
  @JoinColumn(name="autorid", nullable=false)
  private Autor autor;

  @ManyToOne(fetch = FetchType.LAZY, optional=false)
  @JoinColumn(name="literaturaid", nullable=false)
  private Literatura literatura;

    public AutorLiteratura() {
    }

    public AutorLiteratura(Long id, Autor autor, Literatura literatura) {
        this.id = id;
        this.autor = autor;
        this.literatura = literatura;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Literatura getLiteratura() {
        return literatura;
    }

    public void setLiteratura(Literatura literatura) {
        this.literatura = literatura;
    }
  
  
}
