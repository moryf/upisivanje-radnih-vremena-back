package com.konstil.Belezenje.radnih.vremena.controller;

import com.konstil.Belezenje.radnih.vremena.service.TipPorizovdaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tipProizvoda")
@CrossOrigin
public class TipProizvodaController {
    TipPorizovdaService tipPorizovdaService;

    @Autowired
    public TipProizvodaController(TipPorizovdaService tipPorizovdaService) {
        this.tipPorizovdaService = tipPorizovdaService;
    }

    @GetMapping("/svi")
    public ResponseEntity<?> getall() {
        return ResponseEntity.ok(tipPorizovdaService.getall());
    }

    @GetMapping("/allSaProizvodima")
    public ResponseEntity<?> getallSaProizvodima() {
        return ResponseEntity.ok(tipPorizovdaService.getallSaProizvodima());
    }

    @PostMapping("/add/{naziv}")
    public ResponseEntity<?> addTipProizvoda(@PathVariable String naziv) {
        return ResponseEntity.ok(tipPorizovdaService.addTipProizvoda(naziv));
    }

    @GetMapping("/sablon/{id}")
    public ResponseEntity<?> getSablonTipaProizvoda(@PathVariable Integer id) {
        return ResponseEntity.ok(tipPorizovdaService.getSablonTipaProizvoda(id));
    }
}
