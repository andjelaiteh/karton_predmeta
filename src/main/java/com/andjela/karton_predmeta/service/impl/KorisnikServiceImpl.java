/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.service.impl;

import com.andjela.karton_predmeta.entity.Korisnik;
import com.andjela.karton_predmeta.repository.KorisnikRepository;
import com.andjela.karton_predmeta.service.KorisnikService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;




/**
 *
 * @author Andjela
 */

@Service
public class KorisnikServiceImpl implements KorisnikService{
    @Autowired
    private KorisnikRepository korisnikRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Korisnik korisnik = korisnikRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Korisnik nije pronađen: " + username));

        return new User(
                korisnik.getUsername(),
                korisnik.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + korisnik.getUloga().getNaziv()))
        );
    }
}
