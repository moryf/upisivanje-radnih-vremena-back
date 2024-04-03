package com.konstil.Belezenje.radnih.vremena.controller;

import com.konstil.Belezenje.radnih.vremena.service.AlatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alat")
@CrossOrigin
public class AlatController {
    private AlatService alatService;

    @Autowired
    public AlatController(AlatService alatService) {
        this.alatService = alatService;
    }

    @GetMapping("/sve")
    public ResponseEntity<?> vratiSveAlate() {
        return ResponseEntity.ok(alatService.vratiSveAlate());
    }

    @PostMapping("/add/{naziv}")
    public ResponseEntity<?> dodajAlat(@PathVariable String naziv) {
        return ResponseEntity.ok(alatService.dodajAlat(naziv));
    }


}
