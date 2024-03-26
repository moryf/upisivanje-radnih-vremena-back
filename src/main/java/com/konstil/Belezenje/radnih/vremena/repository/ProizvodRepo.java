package com.konstil.Belezenje.radnih.vremena.repository;

import com.konstil.Belezenje.radnih.vremena.domain.Proizvod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProizvodRepo extends JpaRepository<Proizvod, Integer> {
}
