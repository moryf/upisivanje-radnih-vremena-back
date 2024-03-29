package com.konstil.Belezenje.radnih.vremena.repository;

import com.konstil.Belezenje.radnih.vremena.domain.Proizvod;
import com.konstil.Belezenje.radnih.vremena.domain.TipProizvoda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProizvodRepo extends JpaRepository<Proizvod, Integer> {
    List<Proizvod> findByTipProizvoda(TipProizvoda tipProizvoda);
}
