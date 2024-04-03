package com.konstil.Belezenje.radnih.vremena.service;

import com.konstil.Belezenje.radnih.vremena.domain.Alat;
import com.konstil.Belezenje.radnih.vremena.repository.AlatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlatService {
    private AlatRepo alatRepo;

    @Autowired
    public AlatService(AlatRepo alatRepo) {
        this.alatRepo = alatRepo;
    }

    public List<Alat> vratiSveAlate() {

        return alatRepo.findAll();
    }

    public Alat dodajAlat(String naziv) {
        Alat alat = new Alat();
        alat.setNaziv(naziv);
        return alatRepo.save(alat);
    }
}
