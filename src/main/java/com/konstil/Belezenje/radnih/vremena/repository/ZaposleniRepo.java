package com.konstil.Belezenje.radnih.vremena.repository;

import com.konstil.Belezenje.radnih.vremena.domain.Uloga;
import com.konstil.Belezenje.radnih.vremena.domain.Zaposleni;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ZaposleniRepo extends JpaRepository<Zaposleni, Integer> {
    Zaposleni findByKorisnickoIme(String korisnickoIme);

    List<Zaposleni> findAllByActiveOrderByImeAsc(Zaposleni.Active active);

    List<Zaposleni> findAllByActiveAndUloga(Zaposleni.Active active, Uloga uloga);

    Optional<Zaposleni> findByCardUID(String uid);
}
