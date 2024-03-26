package com.konstil.Belezenje.radnih.vremena.controller;

import com.konstil.Belezenje.radnih.vremena.repository.RadnikOperacijaQueueRepo;
import com.konstil.Belezenje.radnih.vremena.service.RadnikOperacijaQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/radnikOperacijaQueue")
@CrossOrigin
public class RadnikOperacijaQueueController {
    RadnikOperacijaQueueService radnikOperacijaQueueService;

    @Autowired
    public RadnikOperacijaQueueController(RadnikOperacijaQueueService radnikOperacijaQueueService) {
        this.radnikOperacijaQueueService = radnikOperacijaQueueService;
    }

    @PostMapping("/zaposleni/{zaposleniId}/operacija/{operacijaId}/radniNalog/{radniNalogSifra}/planirana")
    public ResponseEntity<?> postPlaniranaOperacijaZaposleni(@PathVariable Integer zaposleniId, @PathVariable Integer operacijaId, @PathVariable String radniNalogSifra){
        return ResponseEntity.ok(radnikOperacijaQueueService.postPlaniranaOperacijaZaposleni(zaposleniId,operacijaId,radniNalogSifra));
    }

    @PutMapping("/{id}/aktuelna")
    public ResponseEntity<?> putRadnikoperacijaQueueAktuelna(@PathVariable Long id){
        return  ResponseEntity.ok(radnikOperacijaQueueService.putRadnikoperacijaQueueAktuelna(id));
    }

    @PutMapping("/{id}/zavrsena")
    public ResponseEntity<?> putRadnikoperacijaQueueZavrsena(@PathVariable Long id)
    {
        return  ResponseEntity.ok(radnikOperacijaQueueService.putRadnikoperacijaQueueZavrsena(id));
    }

    @GetMapping("/zaposleni/{id}/aktuelna")
    public ResponseEntity<?> getAktuelnaOperacija(@PathVariable Integer id)
    {
        return ResponseEntity.ok(radnikOperacijaQueueService.getAktuelnaOperacija(id));
    }

    @GetMapping("/zaposleni/{zaposleniId}/planirana")
    public ResponseEntity<?> getPlaniranaOperacija(@PathVariable Integer zaposleniId){
        return ResponseEntity.ok(radnikOperacijaQueueService.getPlaniranaOperacija(zaposleniId));
    }

    @GetMapping("/zaposleni/{zaposleniId}/planirane/sve")
    public ResponseEntity<?> getPlaniraneOperacije(@PathVariable Integer zaposleniId){
        return ResponseEntity.ok(radnikOperacijaQueueService.getPlaniraneOperacije(zaposleniId));
    }

    @GetMapping("/zaposleni/{zaposleniId}/zavrsiAktuelnuZapocniSledecu")
    public ResponseEntity<?> zavrsiAktuelnuZapocniSledecu(@PathVariable Integer zaposleniId){
        return ResponseEntity.ok(radnikOperacijaQueueService.zavrsiAktuelnuZapocniSledecu(zaposleniId));
    }

    @PutMapping("/zapisleni/{zaposleniId}/pauzirajAktuelnu")
    public ResponseEntity<?> pauzirajAktuelnu(@PathVariable Integer zaposleniId){
        try {
            return ResponseEntity.ok(radnikOperacijaQueueService.pauzirajAktuelnu(zaposleniId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRadnikOperacijaQueue(@PathVariable Long id){
        return ResponseEntity.ok(radnikOperacijaQueueService.deleteRadnikOperacijaQueue(id));
    }

    @PostMapping("/zaposleni/{zaposleniId}/operacija/{operacijaId}/radniNalog/{radniNalogSifra}/zameniAktuelnu")
    public ResponseEntity<?> zameniAktuelnuOperaciju(@PathVariable Integer zaposleniId, @PathVariable Integer operacijaId, @PathVariable String radniNalogSifra){
        return ResponseEntity.ok(radnikOperacijaQueueService.zameniAktuelnuOperaciju(zaposleniId,operacijaId,radniNalogSifra));
    }

}
