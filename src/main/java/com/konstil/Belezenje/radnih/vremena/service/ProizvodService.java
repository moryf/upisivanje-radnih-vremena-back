package com.konstil.Belezenje.radnih.vremena.service;

import com.konstil.Belezenje.radnih.vremena.domain.Proizvod;
import com.konstil.Belezenje.radnih.vremena.repository.ProizvodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProizvodService {
    private ProizvodRepo proizvodRepo;

    @Autowired
    public ProizvodService(ProizvodRepo proizvodRepo) {
        this.proizvodRepo = proizvodRepo;
    }

    public List<Proizvod> getall() {
        return proizvodRepo.findAll();
    }

    public Proizvod addProizvod(Proizvod proizvod) {
        return proizvodRepo.save(proizvod);
    }

    public Proizvod getProizvod(Integer id) {
        return proizvodRepo.findById(id).get();
    }
}
