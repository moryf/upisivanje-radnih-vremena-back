package com.konstil.Belezenje.radnih.vremena.repository;

import com.konstil.Belezenje.radnih.vremena.domain.RadniNalog;
import com.konstil.Belezenje.radnih.vremena.domain.StanjeNaloga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RadniNalogRepo extends JpaRepository<RadniNalog, Integer> {

    @Query("SELECT r FROM RadniNalog r WHERE r.stanje = ?1 order by r.sifra asc")
    List<RadniNalog> findByStanje(StanjeNaloga stanjeNaloga);

    RadniNalog findBySifra(String radniNalogSifra);
}
