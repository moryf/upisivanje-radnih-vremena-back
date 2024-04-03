package com.konstil.Belezenje.radnih.vremena.service;

import com.konstil.Belezenje.radnih.vremena.domain.Masina;
import com.konstil.Belezenje.radnih.vremena.repository.MasinaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasinaService {
    private MasinaRepo masinaRepo;

    @Autowired
    public MasinaService(MasinaRepo masinaRepo) {
        this.masinaRepo = masinaRepo;
    }

    public List<Masina> vratiSveMasine() {
        return masinaRepo.findAll();
    }

    public Masina vratiMasinuPoId(Integer id) {
        return masinaRepo.findById(id).orElse(null);
    }

    public Masina sacuvajMasinu(Masina masina) {
        return masinaRepo.save(masina);
    }
}
