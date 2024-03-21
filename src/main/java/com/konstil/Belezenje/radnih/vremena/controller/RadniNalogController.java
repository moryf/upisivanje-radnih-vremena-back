package com.konstil.Belezenje.radnih.vremena.controller;

import com.konstil.Belezenje.radnih.vremena.repository.RadniNalogRepo;
import com.konstil.Belezenje.radnih.vremena.service.RadniNalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/radniNalog")
@CrossOrigin
public class RadniNalogController {
    RadniNalogService radniNalogService;

    @Autowired
    public RadniNalogController(RadniNalogService radniNalogService) {
        this.radniNalogService = radniNalogService;
    }

    @GetMapping("/svi")
    public ResponseEntity<?> getAktivniNalozi() {
        return ResponseEntity.ok(radniNalogService.findAktivniNalozi());
    }

}
