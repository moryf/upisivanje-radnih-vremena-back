package com.konstil.Belezenje.radnih.vremena.repository;

import com.konstil.Belezenje.radnih.vremena.domain.Zaposleni;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZaposleniRepo extends JpaRepository<Zaposleni, Integer> {
    Zaposleni findByKorisnickoIme(String korisnickoIme);

    List<Zaposleni> findAllByActive(Zaposleni.Active active);
}
