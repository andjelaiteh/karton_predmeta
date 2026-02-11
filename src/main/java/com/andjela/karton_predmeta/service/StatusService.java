/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.andjela.karton_predmeta.service;

import com.andjela.karton_predmeta.dto.CreateStatusDto;

/**
 *
 * @author Andjela
 */
public interface StatusService {
    void create(CreateStatusDto csd) throws Exception;
}
