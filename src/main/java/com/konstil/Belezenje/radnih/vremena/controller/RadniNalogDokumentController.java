package com.konstil.Belezenje.radnih.vremena.controller;

import com.konstil.Belezenje.radnih.vremena.repository.RadniNalogDokumentRepo;
import com.konstil.Belezenje.radnih.vremena.service.RadniNalogDokumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/radniNalogDokument")
@CrossOrigin
public class RadniNalogDokumentController {
    RadniNalogDokumentService radniNalogDokumentService;

    @Autowired
    public RadniNalogDokumentController(RadniNalogDokumentService radniNalogDokumentService) {
        this.radniNalogDokumentService = radniNalogDokumentService;
    }
}
