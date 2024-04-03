package com.konstil.Belezenje.radnih.vremena.controller;

import com.konstil.Belezenje.radnih.vremena.service.RNProizvodnjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rnProizvodnja")
@CrossOrigin
public class RNProizvodnjaController {

    private RNProizvodnjaService rnProizvodnjaService;

    @Autowired
    public RNProizvodnjaController(RNProizvodnjaService rnProizvodnjaService) {
        this.rnProizvodnjaService = rnProizvodnjaService;
    }

    @GetMapping("/svi")
    public ResponseEntity<?> getRNProizvodnja() {
        return ResponseEntity.ok(rnProizvodnjaService.getAll());
    }

    @PostMapping("/RNuPRoizvodnju/{id}/{tipRN}/{proizvodId}/{nadlezniId}")
    public ResponseEntity<?> dodajRNuProizvodnju(@PathVariable Integer id, @PathVariable String tipRN, @PathVariable Integer proizvodId, @PathVariable Integer nadlezniId) {
        return ResponseEntity.ok(rnProizvodnjaService.dodajRNuProizvodnju(id, tipRN, proizvodId, nadlezniId));
    }

    @GetMapping("/nadredjeni/{id}")
    public ResponseEntity<?> getRNProizvodnjaNadredjeni(@PathVariable Integer id) {
        return ResponseEntity.ok(rnProizvodnjaService.getRNProizvodnjaNadredjeni(id));
    }
}
