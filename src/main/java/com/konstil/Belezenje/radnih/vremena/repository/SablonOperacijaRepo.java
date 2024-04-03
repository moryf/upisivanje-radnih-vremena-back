package com.konstil.Belezenje.radnih.vremena.repository;

import com.konstil.Belezenje.radnih.vremena.domain.SablonOperacija;
import com.konstil.Belezenje.radnih.vremena.domain.TipProizvoda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SablonOperacijaRepo extends JpaRepository<SablonOperacija,Integer> {
    List<SablonOperacija> findByTipProizvodaOrderByRedosledAsc(TipProizvoda tipProizvoda);
}
