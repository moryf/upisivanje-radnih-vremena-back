package com.konstil.Belezenje.radnih.vremena.repository;

import com.konstil.Belezenje.radnih.vremena.domain.Zaposleni;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZaposleniRepo extends JpaRepository<Zaposleni, Long>{
    Zaposleni findByKorisnickoIme(String korisnickoIme);
}
