package com.konstil.Belezenje.radnih.vremena.controller;

import com.konstil.Belezenje.radnih.vremena.domain.ProizvodOperacija;
import com.konstil.Belezenje.radnih.vremena.service.ProizvodOperacijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proizvodOperacija")
@CrossOrigin
public class ProizvodOperacijaController {
    private ProizvodOperacijaService proizvodOperacijaService;

    @Autowired
    public ProizvodOperacijaController(ProizvodOperacijaService proizvodOperacijaService) {
        this.proizvodOperacijaService = proizvodOperacijaService;
    }

    @GetMapping("/proizvod/{proizvodId}")
    public ResponseEntity<?> getProizvodOperacijaByProizvodId(@PathVariable Integer proizvodId) {
        return ResponseEntity.ok(proizvodOperacijaService.getProizvodOperacijaByProizvodId(proizvodId));
    }

    @PostMapping("/uveziIzSablona/{proizvodId}")
    public ResponseEntity<?> uveziIzSablona(@PathVariable Integer proizvodId) {
        return ResponseEntity.ok(proizvodOperacijaService.uveziIzSablona(proizvodId));
    }

    @PutMapping("/saveAll")
    public ResponseEntity<?> saveAll(@RequestBody List<ProizvodOperacija> proizvodOperacijaList) {
        return ResponseEntity.ok(proizvodOperacijaService.saveAll(proizvodOperacijaList));
    }
}
