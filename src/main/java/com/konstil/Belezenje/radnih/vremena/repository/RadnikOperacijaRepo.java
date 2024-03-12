package com.konstil.Belezenje.radnih.vremena.repository;

import com.konstil.Belezenje.radnih.vremena.domain.RadnikOperacija;
import com.konstil.Belezenje.radnih.vremena.domain.StatusOperacije;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RadnikOperacijaRepo extends JpaRepository<RadnikOperacija, Long> {
    List<RadnikOperacija> findByZaposleniIdAndStatusOperacije(Long zaposleniId, StatusOperacije statusOperacije);
}
