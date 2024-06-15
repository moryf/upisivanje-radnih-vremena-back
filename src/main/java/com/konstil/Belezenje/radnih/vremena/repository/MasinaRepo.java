package com.konstil.Belezenje.radnih.vremena.repository;

import com.konstil.Belezenje.radnih.vremena.domain.Masina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MasinaRepo extends JpaRepository<Masina, Integer> {
}
