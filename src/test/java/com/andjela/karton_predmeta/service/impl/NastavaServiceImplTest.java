/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.service.impl;

import com.andjela.karton_predmeta.dto.CreateNastavaDto;
import com.andjela.karton_predmeta.dto.NastavaStavkaDto;
import com.andjela.karton_predmeta.entity.Nastava;
import com.andjela.karton_predmeta.entity.Predmet;
import com.andjela.karton_predmeta.entity.TipNastave;
import com.andjela.karton_predmeta.repository.NastavaRepository;
import com.andjela.karton_predmeta.repository.PredmetRepository;
import com.andjela.karton_predmeta.repository.TipNastaveRepository;
import exception.NotFoundException;
import exception.ValidationException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;


/**
 *
 * @author Andjela
 */

@ExtendWith(MockitoExtension.class)
class NastavaServiceImplTest {
    
    @Mock
    private NastavaRepository nastavaRepo;

    @Mock
    private PredmetRepository predmetRepo;

    @Mock
    private TipNastaveRepository tipNastaveRepo;

    @InjectMocks
    private NastavaServiceImpl nastavaService;

    @Test
    void create_WhenDtoIsNull_ShouldThrowValidationException() {
        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> nastavaService.create(null)
        );

        assertEquals("Request body je obavezan.", ex.getMessage());
    }

    @Test
    void create_WhenPredmetIdIsNull_ShouldThrowValidationException() {
        CreateNastavaDto dto = new CreateNastavaDto();
        dto.setPredmetId(null);

        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> nastavaService.create(dto)
        );

        assertEquals("predmetId je obavezan.", ex.getMessage());
    }

    @Test
    void create_WhenStavkeIsNull_ShouldThrowValidationException() {
        CreateNastavaDto dto = new CreateNastavaDto();
        dto.setPredmetId(1L);
        dto.setStavke(null);

        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> nastavaService.create(dto)
        );

        assertEquals("stavke su obavezne.", ex.getMessage());
    }

    @Test
    void create_WhenStavkeSizeIsNotFive_ShouldThrowValidationException() {
        CreateNastavaDto dto = new CreateNastavaDto();
        dto.setPredmetId(1L);
        dto.setStavke(List.of(
                createStavka(1L, 1),
                createStavka(2L, 1)
        ));

        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> nastavaService.create(dto)
        );

        assertEquals("Moras poslati tacno 5 stavki (za svih 5 tipova nastave).", ex.getMessage());
    }

    @Test
    void create_WhenStavkaIsNull_ShouldThrowValidationException() {
        CreateNastavaDto dto = new CreateNastavaDto();
        dto.setPredmetId(1L);
        dto.setStavke(Arrays.asList(
                createStavka(1L, 1),
                createStavka(2L, 1),
                null,
                createStavka(4L, 1),
                createStavka(5L, 1)
        ));

        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> nastavaService.create(dto)
        );

        assertEquals("stavke ne smeju sadrzati null.", ex.getMessage());
    }

    @Test
    void create_WhenTipNastaveIdIsNull_ShouldThrowValidationException() {
        CreateNastavaDto dto = new CreateNastavaDto();
        dto.setPredmetId(1L);
        dto.setStavke(List.of(
                createStavka(null, 1),
                createStavka(2L, 1),
                createStavka(3L, 1),
                createStavka(4L, 1),
                createStavka(5L, 0)
        ));

        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> nastavaService.create(dto)
        );

        assertEquals("tipNastaveId je obavezan.", ex.getMessage());
    }

    @Test
    void create_WhenBrojCasovaIsNull_ShouldThrowValidationException() {
        CreateNastavaDto dto = new CreateNastavaDto();
        dto.setPredmetId(1L);
        dto.setStavke(List.of(
                createStavka(1L, null),
                createStavka(2L, 1),
                createStavka(3L, 1),
                createStavka(4L, 1),
                createStavka(5L, 1)
        ));

        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> nastavaService.create(dto)
        );

        assertEquals("brojCasova je obavezan.", ex.getMessage());
    }

    @Test
    void create_WhenBrojCasovaIsNegative_ShouldThrowValidationException() {
        CreateNastavaDto dto = new CreateNastavaDto();
        dto.setPredmetId(1L);
        dto.setStavke(List.of(
                createStavka(1L, -1),
                createStavka(2L, 1),
                createStavka(3L, 1),
                createStavka(4L, 1),
                createStavka(5L, 2)
        ));

        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> nastavaService.create(dto)
        );

        assertEquals("brojCasova ne sme biti negativan.", ex.getMessage());
    }

    @Test
    void create_WhenSumIsNotFour_ShouldThrowValidationException() {
        CreateNastavaDto dto = new CreateNastavaDto();
        dto.setPredmetId(1L);
        dto.setStavke(List.of(
                createStavka(1L, 1),
                createStavka(2L, 1),
                createStavka(3L, 1),
                createStavka(4L, 1),
                createStavka(5L, 1)
        ));

        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> nastavaService.create(dto)
        );

        assertEquals("Zbir casova mora biti tacno 4", ex.getMessage());
    }

    @Test
    void create_WhenPredmetDoesNotExist_ShouldThrowNotFoundException() {
        CreateNastavaDto dto = new CreateNastavaDto();
        dto.setPredmetId(1L);
        dto.setStavke(validStavke());

        when(predmetRepo.findById(1L)).thenReturn(Optional.empty());

        NotFoundException ex = assertThrows(
                NotFoundException.class,
                () -> nastavaService.create(dto)
        );

        assertEquals("Predmet ne postoji.", ex.getMessage());
    }

    @Test
    void create_WhenTipNastaveDoesNotExist_ShouldThrowNotFoundException() {
        CreateNastavaDto dto = new CreateNastavaDto();
        dto.setPredmetId(1L);
        dto.setStavke(validStavke());

        Predmet predmet = new Predmet();
        predmet.setId(1L);

        when(predmetRepo.findById(1L)).thenReturn(Optional.of(predmet));
        when(tipNastaveRepo.findById(1L)).thenReturn(Optional.empty());

        NotFoundException ex = assertThrows(
                NotFoundException.class,
                () -> nastavaService.create(dto)
        );

        assertEquals("Tip nastave ne postoji: 1", ex.getMessage());
    }

    @Test
    void create_WhenDataIsValid_ShouldSaveFiveNastavaRows() {
        CreateNastavaDto dto = new CreateNastavaDto();
        dto.setPredmetId(1L);
        dto.setStavke(validStavke());

        Predmet predmet = new Predmet();
        predmet.setId(1L);

        TipNastave tip1 = new TipNastave();
        tip1.setId(1L);

        TipNastave tip2 = new TipNastave();
        tip2.setId(2L);

        TipNastave tip3 = new TipNastave();
        tip3.setId(3L);

        TipNastave tip4 = new TipNastave();
        tip4.setId(4L);

        TipNastave tip5 = new TipNastave();
        tip5.setId(5L);

        when(predmetRepo.findById(1L)).thenReturn(Optional.of(predmet));
        when(tipNastaveRepo.findById(1L)).thenReturn(Optional.of(tip1));
        when(tipNastaveRepo.findById(2L)).thenReturn(Optional.of(tip2));
        when(tipNastaveRepo.findById(3L)).thenReturn(Optional.of(tip3));
        when(tipNastaveRepo.findById(4L)).thenReturn(Optional.of(tip4));
        when(tipNastaveRepo.findById(5L)).thenReturn(Optional.of(tip5));

        nastavaService.create(dto);

        verify(predmetRepo).findById(1L);
        verify(tipNastaveRepo).findById(1L);
        verify(tipNastaveRepo).findById(2L);
        verify(tipNastaveRepo).findById(3L);
        verify(tipNastaveRepo).findById(4L);
        verify(tipNastaveRepo).findById(5L);
        verify(nastavaRepo, times(5)).save(any(Nastava.class));
    }

    private NastavaStavkaDto createStavka(Long tipNastaveId, Integer brojCasova) {
        NastavaStavkaDto stavka = new NastavaStavkaDto();
        stavka.setTipNastaveId(tipNastaveId);
        stavka.setBrojCasova(brojCasova);
        return stavka;
    }

    private List<NastavaStavkaDto> validStavke() {
        return List.of(
                createStavka(1L, 1),
                createStavka(2L, 1),
                createStavka(3L, 1),
                createStavka(4L, 1),
                createStavka(5L, 0)
        );
    }
}
