package com.konstil.Belezenje.radnih.vremena.controller;

import com.konstil.Belezenje.radnih.vremena.repository.RadnikOperacijaRepo;
import com.konstil.Belezenje.radnih.vremena.service.RadnikOperacijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/radnikOperacija")
@CrossOrigin
public class RadnikOperacijaController {

RadnikOperacijaService radnikOperacijaService;

    @Autowired
    public RadnikOperacijaController(RadnikOperacijaService radnikOperacijaService) {
        this.radnikOperacijaService = radnikOperacijaService;
    }

    @GetMapping("/{zaposleniId}")
    public ResponseEntity<?> getRadnikOperacije(@PathVariable Integer zaposleniId){
        return ResponseEntity.ok(radnikOperacijaService.findAllActiveByZaposleniId(zaposleniId));
    }

}
