/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.service.impl;

import com.andjela.karton_predmeta.dto.CreateLiteraturaStavkaDto;
import com.andjela.karton_predmeta.dto.CreatePredmetLiteraturaDto;
import com.andjela.karton_predmeta.entity.Autor;
import com.andjela.karton_predmeta.entity.AutorLiteratura;
import com.andjela.karton_predmeta.entity.Izdavac;
import com.andjela.karton_predmeta.entity.Literatura;
import com.andjela.karton_predmeta.entity.Predmet;
import com.andjela.karton_predmeta.entity.PredmetLiteratura;
import com.andjela.karton_predmeta.entity.TipLiterature;
import com.andjela.karton_predmeta.repository.AutorLiteraturaRepository;
import com.andjela.karton_predmeta.repository.AutorRepository;
import com.andjela.karton_predmeta.repository.IzdavacRepository;
import com.andjela.karton_predmeta.repository.LiteraturaRepository;
import com.andjela.karton_predmeta.repository.PredmetLiteraturaRepository;
import com.andjela.karton_predmeta.repository.PredmetRepository;
import com.andjela.karton_predmeta.repository.TipLiteratureRepository;
import exception.NotFoundException;
import exception.ValidationException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author Andjela
 */

@ExtendWith(MockitoExtension.class)
class LiteraturaServiceImplTest {
     @Mock
    private PredmetRepository predmetRepo;

    @Mock
    private LiteraturaRepository literaturaRepo;

    @Mock
    private PredmetLiteraturaRepository predmetLiteraturaRepo;

    @Mock
    private TipLiteratureRepository tipRepo;

    @Mock
    private IzdavacRepository izdavacRepo;

    @Mock
    private AutorLiteraturaRepository autorliteraturaRepo;

    @Mock
    private AutorRepository autorRepo;

    @InjectMocks
    private LiteraturaServiceImpl literaturaService;

    @Test
    void createForPredmet_WhenDtoIsNull_ShouldThrowValidationException() {
        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> literaturaService.createForPredmet(null)
        );

        assertEquals("DTO je null", ex.getMessage());
    }

    @Test
    void createForPredmet_WhenPredmetIdIsNull_ShouldThrowValidationException() {
        CreatePredmetLiteraturaDto dto = new CreatePredmetLiteraturaDto();
        dto.setPredmetId(null);

        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> literaturaService.createForPredmet(dto)
        );

        assertEquals("predmetId je obavezan", ex.getMessage());
    }

    @Test
    void createForPredmet_WhenStavkeIsNull_ShouldThrowValidationException() {
        CreatePredmetLiteraturaDto dto = new CreatePredmetLiteraturaDto();
        dto.setPredmetId(1L);
        dto.setStavke(null);

        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> literaturaService.createForPredmet(dto)
        );

        assertEquals("stavke su obavezne", ex.getMessage());
    }

    @Test
    void createForPredmet_WhenStavkeIsEmpty_ShouldThrowValidationException() {
        CreatePredmetLiteraturaDto dto = new CreatePredmetLiteraturaDto();
        dto.setPredmetId(1L);
        dto.setStavke(List.of());

        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> literaturaService.createForPredmet(dto)
        );

        assertEquals("stavke su obavezne", ex.getMessage());
    }

    @Test
    void createForPredmet_WhenPredmetDoesNotExist_ShouldThrowNotFoundException() {
        CreateLiteraturaStavkaDto stavka = new CreateLiteraturaStavkaDto();
        stavka.setNaslov("Knjiga");
        stavka.setGodina(2020);
        stavka.setIzdavacNaziv("Izdavac");
        stavka.setTipLiteratureId(1L);

        CreatePredmetLiteraturaDto dto = new CreatePredmetLiteraturaDto();
        dto.setPredmetId(1L);
        dto.setStavke(List.of(stavka));

        when(predmetRepo.findById(1L)).thenReturn(Optional.empty());

        NotFoundException ex = assertThrows(
                NotFoundException.class,
                () -> literaturaService.createForPredmet(dto)
        );

        assertEquals("Predmet ne postoji", ex.getMessage());
    }

   @Test
void createForPredmet_WhenStavkaIsNull_ShouldThrowValidationException() {
    Predmet predmet = new Predmet();
    predmet.setId(1L);

    CreatePredmetLiteraturaDto dto = new CreatePredmetLiteraturaDto();
    dto.setPredmetId(1L);
    dto.setStavke(Arrays.asList((CreateLiteraturaStavkaDto) null));

    when(predmetRepo.findById(1L)).thenReturn(Optional.of(predmet));

    ValidationException ex = assertThrows(
            ValidationException.class,
            () -> literaturaService.createForPredmet(dto)
    );

    assertEquals("Stavka je null", ex.getMessage());
}

    @Test
    void createForPredmet_WhenNaslovIsEmpty_ShouldThrowValidationException() {
        Predmet predmet = new Predmet();
        predmet.setId(1L);

        CreateLiteraturaStavkaDto stavka = new CreateLiteraturaStavkaDto();
        stavka.setNaslov("");
        stavka.setGodina(2020);
        stavka.setIzdavacNaziv("Izdavac");
        stavka.setTipLiteratureId(1L);

        CreatePredmetLiteraturaDto dto = new CreatePredmetLiteraturaDto();
        dto.setPredmetId(1L);
        dto.setStavke(List.of(stavka));

        when(predmetRepo.findById(1L)).thenReturn(Optional.of(predmet));

        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> literaturaService.createForPredmet(dto)
        );

        assertEquals("naslov je obavezan", ex.getMessage());
    }

    @Test
    void createForPredmet_WhenGodinaIsNegative_ShouldThrowValidationException() {
        Predmet predmet = new Predmet();
        predmet.setId(1L);

        CreateLiteraturaStavkaDto stavka = new CreateLiteraturaStavkaDto();
        stavka.setNaslov("Knjiga");
        stavka.setGodina(-1);
        stavka.setIzdavacNaziv("Izdavac");
        stavka.setTipLiteratureId(1L);

        CreatePredmetLiteraturaDto dto = new CreatePredmetLiteraturaDto();
        dto.setPredmetId(1L);
        dto.setStavke(List.of(stavka));

        when(predmetRepo.findById(1L)).thenReturn(Optional.of(predmet));

        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> literaturaService.createForPredmet(dto)
        );

        assertEquals("godina je obavezna i mora biti >= 0", ex.getMessage());
    }

    @Test
    void createForPredmet_WhenIzdavacNazivIsEmpty_ShouldThrowValidationException() {
        Predmet predmet = new Predmet();
        predmet.setId(1L);

        CreateLiteraturaStavkaDto stavka = new CreateLiteraturaStavkaDto();
        stavka.setNaslov("Knjiga");
        stavka.setGodina(2020);
        stavka.setIzdavacNaziv("");
        stavka.setTipLiteratureId(1L);

        CreatePredmetLiteraturaDto dto = new CreatePredmetLiteraturaDto();
        dto.setPredmetId(1L);
        dto.setStavke(List.of(stavka));

        when(predmetRepo.findById(1L)).thenReturn(Optional.of(predmet));

        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> literaturaService.createForPredmet(dto)
        );

        assertEquals("izdavacNaziv je obavezan", ex.getMessage());
    }

    @Test
    void createForPredmet_WhenTipLiteratureIdIsNull_ShouldThrowValidationException() {
        Predmet predmet = new Predmet();
        predmet.setId(1L);

        CreateLiteraturaStavkaDto stavka = new CreateLiteraturaStavkaDto();
        stavka.setNaslov("Knjiga");
        stavka.setGodina(2020);
        stavka.setIzdavacNaziv("Izdavac");
        stavka.setTipLiteratureId(null);

        CreatePredmetLiteraturaDto dto = new CreatePredmetLiteraturaDto();
        dto.setPredmetId(1L);
        dto.setStavke(List.of(stavka));

        when(predmetRepo.findById(1L)).thenReturn(Optional.of(predmet));

        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> literaturaService.createForPredmet(dto)
        );

        assertEquals("tipLiteratureId je obavezan", ex.getMessage());
    }

    @Test
    void createForPredmet_WhenTipLiteratureDoesNotExist_ShouldThrowValidationException() {
        Predmet predmet = new Predmet();
        predmet.setId(1L);

        CreateLiteraturaStavkaDto stavka = new CreateLiteraturaStavkaDto();
        stavka.setNaslov("Knjiga");
        stavka.setGodina(2020);
        stavka.setIzdavacNaziv("Izdavac");
        stavka.setTipLiteratureId(1L);

        CreatePredmetLiteraturaDto dto = new CreatePredmetLiteraturaDto();
        dto.setPredmetId(1L);
        dto.setStavke(List.of(stavka));

        when(predmetRepo.findById(1L)).thenReturn(Optional.of(predmet));
        when(tipRepo.findById(1L)).thenReturn(Optional.empty());

        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> literaturaService.createForPredmet(dto)
        );

        assertEquals("Tip literature ne postoji", ex.getMessage());
    }

    @Test
    void createForPredmet_WhenDataIsValidAndIzdavacExistsAndAutorExists_ShouldSaveEverything() {
        CreateLiteraturaStavkaDto stavka = new CreateLiteraturaStavkaDto();
        stavka.setNaslov("Softversko inzenjerstvo");
        stavka.setGodina(2023);
        stavka.setIzdavacNaziv("ETF izdavastvo");
        stavka.setTipLiteratureId(1L);
        stavka.setAutori(List.of("Petar Petrovic"));

        CreatePredmetLiteraturaDto dto = new CreatePredmetLiteraturaDto();
        dto.setPredmetId(1L);
        dto.setStavke(List.of(stavka));

        Predmet predmet = new Predmet();
        predmet.setId(1L);

        TipLiterature tip = new TipLiterature();
        tip.setId(1L);

        Izdavac izdavac = new Izdavac();
        izdavac.setId(1L);
        izdavac.setNaziv("ETF izdavastvo");

        Literatura literatura = new Literatura();
        literatura.setId(1L);
        literatura.setNaslov("Softversko inzenjerstvo");
        literatura.setGodina(2023);
        literatura.setIzdavac(izdavac);

        Autor autor = new Autor();
        autor.setId(1L);
        autor.setImePrezime("Petar Petrovic");

        when(predmetRepo.findById(1L)).thenReturn(Optional.of(predmet));
        when(tipRepo.findById(1L)).thenReturn(Optional.of(tip));
        when(izdavacRepo.findByNaziv("ETF izdavastvo")).thenReturn(Optional.of(izdavac));
        when(literaturaRepo.save(any(Literatura.class))).thenReturn(literatura);
        when(autorRepo.findByImePrezime("Petar Petrovic")).thenReturn(Optional.of(autor));

        literaturaService.createForPredmet(dto);

        verify(predmetRepo).findById(1L);
        verify(tipRepo).findById(1L);
        verify(izdavacRepo).findByNaziv("ETF izdavastvo");
        verify(literaturaRepo).save(any(Literatura.class));
        verify(predmetLiteraturaRepo).save(any(PredmetLiteratura.class));
        verify(autorRepo).findByImePrezime("Petar Petrovic");
        verify(autorliteraturaRepo).save(any(AutorLiteratura.class));
    }

    @Test
    void createForPredmet_WhenIzdavacDoesNotExist_ShouldCreateNewIzdavac() {
        CreateLiteraturaStavkaDto stavka = new CreateLiteraturaStavkaDto();
        stavka.setNaslov("Knjiga");
        stavka.setGodina(2022);
        stavka.setIzdavacNaziv("Novi izdavac");
        stavka.setTipLiteratureId(1L);
        stavka.setAutori(null);

        CreatePredmetLiteraturaDto dto = new CreatePredmetLiteraturaDto();
        dto.setPredmetId(1L);
        dto.setStavke(List.of(stavka));

        Predmet predmet = new Predmet();
        predmet.setId(1L);

        TipLiterature tip = new TipLiterature();
        tip.setId(1L);

        Izdavac noviIzdavac = new Izdavac();
        noviIzdavac.setId(2L);
        noviIzdavac.setNaziv("Novi izdavac");

        Literatura literatura = new Literatura();
        literatura.setId(1L);
        literatura.setNaslov("Knjiga");
        literatura.setGodina(2022);
        literatura.setIzdavac(noviIzdavac);

        when(predmetRepo.findById(1L)).thenReturn(Optional.of(predmet));
        when(tipRepo.findById(1L)).thenReturn(Optional.of(tip));
        when(izdavacRepo.findByNaziv("Novi izdavac")).thenReturn(Optional.empty());
        when(izdavacRepo.save(any(Izdavac.class))).thenReturn(noviIzdavac);
        when(literaturaRepo.save(any(Literatura.class))).thenReturn(literatura);

        literaturaService.createForPredmet(dto);

        verify(izdavacRepo).save(any(Izdavac.class));
        verify(literaturaRepo).save(any(Literatura.class));
        verify(predmetLiteraturaRepo).save(any(PredmetLiteratura.class));
    }

    @Test
    void createForPredmet_WhenAutorDoesNotExist_ShouldCreateNewAutor() {
        CreateLiteraturaStavkaDto stavka = new CreateLiteraturaStavkaDto();
        stavka.setNaslov("Knjiga");
        stavka.setGodina(2022);
        stavka.setIzdavacNaziv("Izdavac");
        stavka.setTipLiteratureId(1L);
        stavka.setAutori(List.of("Novi Autor"));

        CreatePredmetLiteraturaDto dto = new CreatePredmetLiteraturaDto();
        dto.setPredmetId(1L);
        dto.setStavke(List.of(stavka));

        Predmet predmet = new Predmet();
        predmet.setId(1L);

        TipLiterature tip = new TipLiterature();
        tip.setId(1L);

        Izdavac izdavac = new Izdavac();
        izdavac.setId(1L);
        izdavac.setNaziv("Izdavac");

        Literatura literatura = new Literatura();
        literatura.setId(1L);
        literatura.setNaslov("Knjiga");
        literatura.setGodina(2022);
        literatura.setIzdavac(izdavac);

        Autor noviAutor = new Autor();
        noviAutor.setId(2L);
        noviAutor.setImePrezime("Novi Autor");

        when(predmetRepo.findById(1L)).thenReturn(Optional.of(predmet));
        when(tipRepo.findById(1L)).thenReturn(Optional.of(tip));
        when(izdavacRepo.findByNaziv("Izdavac")).thenReturn(Optional.of(izdavac));
        when(literaturaRepo.save(any(Literatura.class))).thenReturn(literatura);
        when(autorRepo.findByImePrezime("Novi Autor")).thenReturn(Optional.empty());
        when(autorRepo.save(any(Autor.class))).thenReturn(noviAutor);

        literaturaService.createForPredmet(dto);

        verify(autorRepo).save(any(Autor.class));
        verify(autorliteraturaRepo).save(any(AutorLiteratura.class));
    }

    @Test
void createForPredmet_WhenAutorIsEmpty_ShouldSkipAutor() {
    CreateLiteraturaStavkaDto stavka = new CreateLiteraturaStavkaDto();
    stavka.setNaslov("Knjiga");
    stavka.setGodina(2022);
    stavka.setIzdavacNaziv("Izdavac");
    stavka.setTipLiteratureId(1L);
    stavka.setAutori(Arrays.asList("", "   ", null));

    CreatePredmetLiteraturaDto dto = new CreatePredmetLiteraturaDto();
    dto.setPredmetId(1L);
    dto.setStavke(List.of(stavka));

    Predmet predmet = new Predmet();
    predmet.setId(1L);

    TipLiterature tip = new TipLiterature();
    tip.setId(1L);

    Izdavac izdavac = new Izdavac();
    izdavac.setId(1L);
    izdavac.setNaziv("Izdavac");

    Literatura literatura = new Literatura();
    literatura.setId(1L);
    literatura.setNaslov("Knjiga");
    literatura.setGodina(2022);
    literatura.setIzdavac(izdavac);

    when(predmetRepo.findById(1L)).thenReturn(Optional.of(predmet));
    when(tipRepo.findById(1L)).thenReturn(Optional.of(tip));
    when(izdavacRepo.findByNaziv("Izdavac")).thenReturn(Optional.of(izdavac));
    when(literaturaRepo.save(any(Literatura.class))).thenReturn(literatura);

    literaturaService.createForPredmet(dto);

    verify(literaturaRepo).save(any(Literatura.class));
    verify(predmetLiteraturaRepo).save(any(PredmetLiteratura.class));
}
}
