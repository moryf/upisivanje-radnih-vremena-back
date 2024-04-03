package com.konstil.Belezenje.radnih.vremena.repository;

import com.konstil.Belezenje.radnih.vremena.domain.RNProizvodnja;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RNProizvodnjaRepo extends JpaRepository<RNProizvodnja, Integer> {
    List<RNProizvodnja> findAllByNadlezniId(Integer id);
}
