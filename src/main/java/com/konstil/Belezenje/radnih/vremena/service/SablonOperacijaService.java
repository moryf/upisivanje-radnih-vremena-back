package com.konstil.Belezenje.radnih.vremena.service;

import com.konstil.Belezenje.radnih.vremena.repository.SablonOperacijaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SablonOperacijaService {
    private SablonOperacijaRepo sablonOperacijaRepo;

    @Autowired
    public SablonOperacijaService (SablonOperacijaRepo sablonOperacijaRepo){
        this.sablonOperacijaRepo = sablonOperacijaRepo;
    }



}
