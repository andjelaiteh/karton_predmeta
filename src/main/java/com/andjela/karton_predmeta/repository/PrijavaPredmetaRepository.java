/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.andjela.karton_predmeta.repository;

import com.andjela.karton_predmeta.entity.PrijavaPredmeta;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Andjela
 */
public interface PrijavaPredmetaRepository extends JpaRepository<PrijavaPredmeta, Long> {
    
    List<PrijavaPredmeta> findByKorisnik_Id(Long korisnikId);
    boolean existsByKorisnik_Id(Long korisnikId);
    @Transactional
    void deleteByKorisnik_Id(Long korisnikId);
}
