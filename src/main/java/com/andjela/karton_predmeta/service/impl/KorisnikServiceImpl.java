/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.service.impl;

import com.andjela.karton_predmeta.entity.Korisnik;
import com.andjela.karton_predmeta.entity.Uloga;
import com.andjela.karton_predmeta.repository.KorisnikRepository;
import com.andjela.karton_predmeta.repository.UlogaRepository;
import com.andjela.karton_predmeta.service.KorisnikService;
import io.getunleash.Unleash;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;




/**
 *
 * @author Andjela
 */

@Service
public class KorisnikServiceImpl implements KorisnikService{
    @Autowired
    private KorisnikRepository korisnikRepository;
    
    @Autowired
    private UlogaRepository ulogaRepository;
    
    @Autowired
    private Unleash unleash;

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

    @Override
    public void registruj(String username, String password) {
    if (username == null || username.trim().isEmpty())
            throw new RuntimeException("Korisničko ime je obavezno.");
        if (password == null || password.trim().isEmpty())
            throw new RuntimeException("Lozinka je obavezna.");
        
        proveriLozinku(password);

        if (korisnikRepository.findByUsername(username).isPresent())
            throw new RuntimeException("Korisničko ime je zauzeto.");

        Uloga studentUloga = ulogaRepository.findByNaziv("STUDENT")
                .orElseThrow(() -> new RuntimeException("Uloga STUDENT ne postoji."));

        Korisnik noviKorisnik = new Korisnik();
        noviKorisnik.setUsername(username.trim());
        noviKorisnik.setPassword(new BCryptPasswordEncoder().encode(password));
        noviKorisnik.setUloga(studentUloga);

        korisnikRepository.save(noviKorisnik);
    }

    private void proveriLozinku(String password) {
        if (unleash.isEnabled("strong-password")) {
            // bar 10 karaktera, bar 1 broj, bar 1 veliko slovo, bar 1 specijalni znak
            if (password.length() < 10)
                throw new RuntimeException("Lozinka mora imati bar 10 karaktera.");
            if (!password.matches(".*[0-9].*"))
                throw new RuntimeException("Lozinka mora sadržati bar jedan broj.");
            if (!password.matches(".*[A-ZŠĐČĆŽ].*"))
                throw new RuntimeException("Lozinka mora sadržati bar jedno veliko slovo.");
            if (!password.matches(".*[^a-zA-Z0-9].*"))
                throw new RuntimeException("Lozinka mora sadržati bar jedan specijalni znak.");
        } else {
            // pravila: bar 6 karaktera
            if (password.length() < 6)
                throw new RuntimeException("Lozinka mora imati bar 6 karaktera.");
        }
    }
}
