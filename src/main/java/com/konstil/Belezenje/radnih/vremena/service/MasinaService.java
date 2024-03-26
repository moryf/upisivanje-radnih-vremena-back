package com.konstil.Belezenje.radnih.vremena.service;

import com.konstil.Belezenje.radnih.vremena.repository.MasinaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasinaService {
    private MasinaRepo masinaRepo;

    @Autowired
    public MasinaService(MasinaRepo masinaRepo) {
        this.masinaRepo = masinaRepo;
    }
}
