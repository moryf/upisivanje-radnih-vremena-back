package com.konstil.Belezenje.radnih.vremena.controller;


import com.konstil.Belezenje.radnih.vremena.service.OperacijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operacija")
@CrossOrigin
public class OperacijaController {
    @Autowired
    OperacijaService operacijaService;

    @GetMapping("/svi")
    public ResponseEntity<?> getAllOperacija() {
        return ResponseEntity.ok(operacijaService.getAllOperacija());
    }
}
