package com.konstil.Belezenje.radnih.vremena.controller;

import com.konstil.Belezenje.radnih.vremena.domain.Zaposleni;
import com.konstil.Belezenje.radnih.vremena.dto.LoginRequest;
import com.konstil.Belezenje.radnih.vremena.service.ZaposleniService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/zaposleni")
@CrossOrigin
@Log4j2
public class ZaposleniController {
    ZaposleniService zaposleniService;

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public ZaposleniController(ZaposleniService zaposleniService, SimpMessagingTemplate simpMessagingTemplate) {
        this.zaposleniService = zaposleniService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            return ResponseEntity.ok(zaposleniService.login(loginRequest));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(Zaposleni zaposleni) {
        return ResponseEntity.ok(zaposleniService.register(zaposleni));
    }

    @GetMapping("/svi")
    public ResponseEntity<?> svi() {
        return ResponseEntity.ok(zaposleniService.getSviAktivni());
    }

    @GetMapping("/{id}")
    public  ResponseEntity<?> getZaposleniById(@PathVariable Integer id){
        return ResponseEntity.ok(zaposleniService.getZaposleniById(id));
    }

    @PostMapping("/cardLogin/{cardId}")
    public ResponseEntity<?> cardLogin(@PathVariable String cardId) {
        try {
            log.info("Card login: " + cardId);
            Zaposleni zaposleni = zaposleniService.cardLogin(cardId);
            simpMessagingTemplate.convertAndSend("/topic/cardLogin", zaposleni);
            return ResponseEntity.ok(zaposleni);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
