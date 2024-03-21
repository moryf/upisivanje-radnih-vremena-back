package com.konstil.Belezenje.radnih.vremena.service;

import com.konstil.Belezenje.radnih.vremena.repository.RadniNalogDokumentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RadniNalogDokumentService {
    RadniNalogDokumentRepo radniNalogDokumentRepo;

    @Autowired
    public RadniNalogDokumentService(RadniNalogDokumentRepo radniNalogDokumentRepo) {
        this.radniNalogDokumentRepo = radniNalogDokumentRepo;
    }
}
