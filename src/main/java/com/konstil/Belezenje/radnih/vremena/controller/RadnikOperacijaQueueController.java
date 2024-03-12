package com.konstil.Belezenje.radnih.vremena.controller;

import com.konstil.Belezenje.radnih.vremena.service.RadnikOperacijaQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/radnikOperacijaQueue")
@CrossOrigin
public class RadnikOperacijaQueueController {
    @Autowired
    RadnikOperacijaQueueService radnikOperacijaQueueService;

    @PostMapping("/zaposleni/{zaposleniId}/operacija/{operacijaId}/radniNalog/{radniNalogId}/planirana")
    public ResponseEntity<?> planirajOperaciju(@PathVariable Long zaposleniId,@PathVariable Long operacijaId,@PathVariable String radniNalogId) {
        return ResponseEntity.ok(radnikOperacijaQueueService.planirajOperaciju(zaposleniId, operacijaId, radniNalogId));
    }

    @PutMapping("/{id}/aktuelna")
    public ResponseEntity<?> postaviAktuelnu(@PathVariable Long id) {
        return ResponseEntity.ok(radnikOperacijaQueueService.postaviAktuelnu(id));
    }

    @PostMapping("{id}/zavrsena")
    public ResponseEntity<?> zavrsiOperaciju(@PathVariable Long id) {
        return ResponseEntity.ok(radnikOperacijaQueueService.zavrsiOperaciju(id));
    }

    @GetMapping("/zaposleni/{zaposleniId}/zavrsiAktuelnuZapocniSledecu")
    public ResponseEntity<?> zavrsiAktuelnuZapocniSledecu(@PathVariable Long zaposleniId) {
        return ResponseEntity.ok(radnikOperacijaQueueService.zavrsiAktuelnuZapocniSledecu(zaposleniId));
    }

    @GetMapping("/zaposleni/{zaposleniId}/aktuelna")
    public ResponseEntity<?> getAktuelnaOperacija(@PathVariable Long zaposleniId) {
        return ResponseEntity.ok(radnikOperacijaQueueService.getAktuelnaOperacija(zaposleniId));
    }

    @GetMapping("/zaposleni/{zaposleniId}/planirana")
    public ResponseEntity<?> getPlaniranaOperacija(@PathVariable Long zaposleniId) {
        return ResponseEntity.ok(radnikOperacijaQueueService.getPlaniranaOperacija(zaposleniId));
    }

    @GetMapping("/zaposleni/{zaposleniId}/planirane/sve")
    public ResponseEntity<?> getPlaniraneOperacije(@PathVariable Long zaposleniId) {
        return ResponseEntity.ok(radnikOperacijaQueueService.getPlaniraneOperacije(zaposleniId));
    }

}
