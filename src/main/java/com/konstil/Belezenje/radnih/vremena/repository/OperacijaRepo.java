package com.konstil.Belezenje.radnih.vremena.repository;

import com.konstil.Belezenje.radnih.vremena.domain.Operacija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OperacijaRepo extends JpaRepository<Operacija, Integer>{

}
