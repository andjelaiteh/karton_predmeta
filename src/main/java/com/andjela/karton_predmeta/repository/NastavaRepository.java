/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.andjela.karton_predmeta.repository;

import com.andjela.karton_predmeta.entity.Nastava;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Andjela
 */
public interface NastavaRepository extends JpaRepository<Nastava, Long>{
    List<Nastava> findByPredmet_Id(Long predmetId);
}
