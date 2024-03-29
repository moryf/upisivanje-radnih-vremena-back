package com.konstil.Belezenje.radnih.vremena.controller;

import com.konstil.Belezenje.radnih.vremena.domain.Proizvod;
import com.konstil.Belezenje.radnih.vremena.service.ProizvodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proizvod")
@CrossOrigin
public class ProizvodController {
    ProizvodService proizvodService;

    @Autowired
    public ProizvodController(ProizvodService proizvodService) {
        this.proizvodService = proizvodService;
    }

    @GetMapping("/svi")
    public ResponseEntity<?> getall() {
        return ResponseEntity.ok(proizvodService.getall());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProizvod(@RequestBody Proizvod proizvod) {
        return ResponseEntity.ok(proizvodService.addProizvod(proizvod));
    }
}
