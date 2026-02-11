/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.andjela.karton_predmeta.service;

import com.andjela.karton_predmeta.dto.StudijskiProgramDto;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andjela
 */

public interface StudijskiProgramService {
    List<StudijskiProgramDto> findAll();
}
