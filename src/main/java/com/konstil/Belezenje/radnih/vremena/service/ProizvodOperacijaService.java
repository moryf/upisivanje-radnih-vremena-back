package com.konstil.Belezenje.radnih.vremena.service;

import com.konstil.Belezenje.radnih.vremena.repository.ProizvodOperacijaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProizvodOperacijaService {
    private ProizvodOperacijaRepo proizvodOperacijaRepo;

    @Autowired
    public ProizvodOperacijaService(ProizvodOperacijaRepo proizvodOperacijaRepo) {
        this.proizvodOperacijaRepo = proizvodOperacijaRepo;
    }
}
