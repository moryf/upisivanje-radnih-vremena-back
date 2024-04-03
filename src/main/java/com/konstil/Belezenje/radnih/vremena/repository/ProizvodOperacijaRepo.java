package com.konstil.Belezenje.radnih.vremena.repository;

import com.konstil.Belezenje.radnih.vremena.domain.ProizvodOperacija;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProizvodOperacijaRepo extends JpaRepository<ProizvodOperacija, Integer> {
    List<ProizvodOperacija> findByProizvodId(Integer proizvodId);
}
