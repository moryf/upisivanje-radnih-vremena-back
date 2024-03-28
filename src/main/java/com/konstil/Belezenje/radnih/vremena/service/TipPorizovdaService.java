package com.konstil.Belezenje.radnih.vremena.service;

import com.konstil.Belezenje.radnih.vremena.repository.TipProizvodaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipPorizovdaService {
    private TipProizvodaRepo tipProizvodaRepo;
    @Autowired
    public TipPorizovdaService(TipProizvodaRepo tipProizvodaRepo) {
        this.tipProizvodaRepo = tipProizvodaRepo;
    }
}
