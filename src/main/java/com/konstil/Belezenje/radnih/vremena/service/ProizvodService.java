package com.konstil.Belezenje.radnih.vremena.service;

import com.konstil.Belezenje.radnih.vremena.repository.ProizvodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProizvodService {
    private ProizvodRepo proizvodRepo;

    @Autowired
    public ProizvodService(ProizvodRepo proizvodRepo) {
        this.proizvodRepo = proizvodRepo;
    }
}
