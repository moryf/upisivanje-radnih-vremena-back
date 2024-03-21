package com.konstil.Belezenje.radnih.vremena.service;

import com.konstil.Belezenje.radnih.vremena.domain.Operacija;
import com.konstil.Belezenje.radnih.vremena.repository.OperacijaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperacijaService {
    OperacijaRepo operacijaRepo;

    @Autowired
    public OperacijaService(OperacijaRepo operacijaRepo) {
        this.operacijaRepo = operacijaRepo;
    }

    public List<Operacija> svi() {
        return operacijaRepo.findAll();
    }
}
