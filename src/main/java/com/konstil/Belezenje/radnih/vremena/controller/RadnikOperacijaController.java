package com.konstil.Belezenje.radnih.vremena.controller;

import com.konstil.Belezenje.radnih.vremena.service.RadnikOperacijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/radnikOperacija")
@CrossOrigin
public class RadnikOperacijaController {
    @Autowired
    RadnikOperacijaService radnikOperacijaService;


}
