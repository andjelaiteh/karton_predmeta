/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.andjela.karton_predmeta.repository;

import com.andjela.karton_predmeta.entity.Autor;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Andjela
 */
public interface AutorRepository extends JpaRepository<Autor, Long>{
    Optional<Autor> findByImePrezime(String imePrezime);
}
