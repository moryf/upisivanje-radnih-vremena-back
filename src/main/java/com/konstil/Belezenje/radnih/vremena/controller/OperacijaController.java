package com.konstil.Belezenje.radnih.vremena.controller;

import com.konstil.Belezenje.radnih.vremena.domain.Operacija;
import com.konstil.Belezenje.radnih.vremena.repository.OperacijaRepo;
import com.konstil.Belezenje.radnih.vremena.service.OperacijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/operacija")
@CrossOrigin
public class OperacijaController {

    OperacijaService operacijaService;

    @Autowired
    public OperacijaController(OperacijaService operacijaService) {
        this.operacijaService = operacijaService;
    }


    @GetMapping("/svi")
    public ResponseEntity<?> svi(){
        List<Operacija> operacije = operacijaService.svi();
        return ResponseEntity.ok(operacije);
    }
}
