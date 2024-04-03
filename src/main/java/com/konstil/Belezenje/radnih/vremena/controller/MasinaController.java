package com.konstil.Belezenje.radnih.vremena.controller;

import com.konstil.Belezenje.radnih.vremena.domain.Masina;
import com.konstil.Belezenje.radnih.vremena.service.MasinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/masina")
@CrossOrigin
public class MasinaController {
    private MasinaService masinaService;

    @Autowired
    public MasinaController(MasinaService masinaService) {
        this.masinaService = masinaService;
    }

    @GetMapping("/sve")
    public ResponseEntity<?> vratiSveMasine() {
        return ResponseEntity.ok(masinaService.vratiSveMasine());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> vratiMasinuPoId(@PathVariable Integer id) {
        return ResponseEntity.ok(masinaService.vratiMasinuPoId(id));
    }

    @PostMapping("/save")
    public  ResponseEntity<?> sacuvajMasinu(@RequestBody Masina masina) {
        return ResponseEntity.ok(masinaService.sacuvajMasinu(masina));
    }
}
