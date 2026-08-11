/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.andjela.karton_predmeta.repository;

import com.andjela.karton_predmeta.entity.Korisnik;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Andjela
 */
public interface KorisnikRepository extends JpaRepository<Korisnik, Long>{
     Optional<Korisnik> findByUsername(String username);
     List<Korisnik> findByUloga_Naziv(String naziv);
}
