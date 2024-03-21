package com.konstil.Belezenje.radnih.vremena.repository;

import com.konstil.Belezenje.radnih.vremena.domain.RadnikOperacija;
import com.konstil.Belezenje.radnih.vremena.domain.StanjeNaloga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RadnikOperacijaRepo extends JpaRepository<RadnikOperacija, Integer>

{
@Query("SELECT ro FROM RadnikOperacija ro WHERE ro.radniNalog.stanje='ACTIVE' AND ro.zaposleni.active='DA'")
    List<RadnikOperacija> findActive();

public List<RadnikOperacija> findAllByZaposleniIdAndRadniNalogStanje(Integer zaposleniId, StanjeNaloga stanje);
}
