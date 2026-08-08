/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.andjela.karton_predmeta.service;

import java.util.List;
import java.util.Map;


/**
 *
 * @author Andjela
 */

public interface PrijavaPredmetaService {
    Map<String, Object> podaciKorisnika();
    List<Map<String, Object>> obavezniPredmeti();
    List<Map<String, Object>> izborniPredmeti();
    void sacuvajPrijavu(List<Long> izborniIds);
    boolean postojiPrijava();
    List<Long> mojiIzborni();
    void izmeniPrijavu(List<Long> izborniIds);
}
