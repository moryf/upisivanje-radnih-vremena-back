package com.konstil.Belezenje.radnih.vremena.controller;

import com.konstil.Belezenje.radnih.vremena.domain.SablonOperacija;
import com.konstil.Belezenje.radnih.vremena.service.SablonOperacijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sablonOperacija")
@CrossOrigin
public class SablonOperacijaController {

    SablonOperacijaService sablonOperacijaService;

    @Autowired
    public SablonOperacijaController(SablonOperacijaService sablonOperacijaService) {
        this.sablonOperacijaService = sablonOperacijaService;
    }

    @PostMapping("/add/{tipProizvodaId}/{operacijaId}")
    public ResponseEntity<?> addSablonOperacija(@PathVariable Integer tipProizvodaId, @PathVariable Integer operacijaId) {
        return ResponseEntity.ok(sablonOperacijaService.addSablonOperacija(tipProizvodaId, operacijaId));
    }


    @PutMapping("/saveAll")
    public ResponseEntity<?> saveAll(@RequestBody List<SablonOperacija> sablonOperacijaList) {
        return ResponseEntity.ok(sablonOperacijaService.saveAll(sablonOperacijaList));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSablonOperacija(@PathVariable Integer id) {
        sablonOperacijaService.deleteSablonOperacija(id);
        return ResponseEntity.ok().build();
    }
}
