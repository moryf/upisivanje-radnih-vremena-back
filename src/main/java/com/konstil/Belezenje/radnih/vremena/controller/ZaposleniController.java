package com.konstil.Belezenje.radnih.vremena.controller;


import com.konstil.Belezenje.radnih.vremena.domain.Zaposleni;
import com.konstil.Belezenje.radnih.vremena.dto.LoginRequest;
import com.konstil.Belezenje.radnih.vremena.service.ZaposleniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/zaposleni")
@CrossOrigin
public class ZaposleniController {
    @Autowired
    ZaposleniService zaposleniService;


    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            return ResponseEntity.ok(zaposleniService.login(loginRequest.getKorisnickoIme(), loginRequest.getLozinka()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Zaposleni zaposleni) {
        try {
            return ResponseEntity.ok(zaposleniService.register(zaposleni));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/svi")
    public ResponseEntity<?> getAllZaposleni() {
        return ResponseEntity.ok(zaposleniService.getAllZaposleni());
    }
}
