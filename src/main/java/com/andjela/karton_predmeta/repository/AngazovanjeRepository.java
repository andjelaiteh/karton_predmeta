/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.andjela.karton_predmeta.repository;

import com.andjela.karton_predmeta.entity.Angazovanje;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Andjela
 */
public interface AngazovanjeRepository extends JpaRepository<Angazovanje, Long> {
    
}
