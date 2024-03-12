package com.konstil.Belezenje.radnih.vremena.service;

import com.konstil.Belezenje.radnih.vremena.domain.RadnikOperacija;
import com.konstil.Belezenje.radnih.vremena.domain.RadnikOperacijaQueue;
import com.konstil.Belezenje.radnih.vremena.domain.StatusOperacije;
import com.konstil.Belezenje.radnih.vremena.exception.BackEndError;
import com.konstil.Belezenje.radnih.vremena.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RadnikOperacijaQueueService {
    @Autowired
    RadnikOperacijaQueueRepo radnikOperacijaQueueRepo;
    @Autowired
    ZaposleniRepo zaposleniRepo;
    @Autowired
    OperacijaRepo operacijaRepo;
    @Autowired
    RadniNalogRepo radniNalogRepo;
    @Autowired
    RadnikOperacijaRepo radnikOperacijaRepo;

    public RadnikOperacijaQueue planirajOperaciju(Long zaposleniId, Long operacijaId, String radniNalogId) {
        if(zaposleniRepo.findById(zaposleniId).isEmpty()){
            throw new BackEndError("Zaposleni sa id " + zaposleniId + " ne postoji");
        }
        if(operacijaRepo.findById(operacijaId).isEmpty()){
            throw new BackEndError("Operacija sa id " + operacijaId + " ne postoji");
        }
        if (radniNalogRepo.findById(radniNalogId).isEmpty()){
            throw new BackEndError("Radni nalog sa id " + radniNalogId + " ne postoji");
        }
        RadnikOperacijaQueue radnikOperacijaQueue = new RadnikOperacijaQueue();
        radnikOperacijaQueue.setZaposleni(zaposleniRepo.findById(zaposleniId).get());
        radnikOperacijaQueue.setOperacija(operacijaRepo.findById(operacijaId).get());
        radnikOperacijaQueue.setRadniNalog(radniNalogRepo.findById(radniNalogId).get());
        radnikOperacijaQueue.setStatusOperacije(StatusOperacije.PLANIRANA);


        return radnikOperacijaQueueRepo.save(radnikOperacijaQueue);
    }

    public RadnikOperacijaQueue postaviAktuelnu(Long id) {
        RadnikOperacijaQueue radnikOperacijaQueue = radnikOperacijaQueueRepo.findById(id).get();
        radnikOperacijaQueue.setStatusOperacije(StatusOperacije.AKTUELNA);
        radnikOperacijaQueue.setPocetak(new java.util.Date());
        return radnikOperacijaQueueRepo.save(radnikOperacijaQueue);
    }

    public RadnikOperacija zavrsiOperaciju(Long id) {
        RadnikOperacijaQueue radnikOperacijaQueue = radnikOperacijaQueueRepo.findById(id).get();
        RadnikOperacija radnikOperacija = new RadnikOperacija();
        radnikOperacija.setZaposleni(radnikOperacijaQueue.getZaposleni());
        radnikOperacija.setOperacija(radnikOperacijaQueue.getOperacija());
        radnikOperacija.setRadniNalog(radnikOperacijaQueue.getRadniNalog());
        radnikOperacija.setPocetak(radnikOperacijaQueue.getPocetak());
        radnikOperacija.setKraj(new Date());
        return radnikOperacijaRepo.save(radnikOperacija);
    }

    public RadnikOperacijaQueue zavrsiAktuelnuZapocniSledecu(Long zaposleniId) {
        RadnikOperacijaQueue aktuelna = radnikOperacijaQueueRepo.findFirstByZaposleniIdAndStatusOperacije(zaposleniId, StatusOperacije.AKTUELNA);
        if(aktuelna != null){
            zavrsiOperaciju(aktuelna.getId());
        }else {
            throw new BackEndError("Nema aktuelne operacije za zaposlenog sa id " + zaposleniId);
        }
        RadnikOperacijaQueue sledeca = radnikOperacijaQueueRepo.findFirstByZaposleniIdAndStatusOperacije(zaposleniId, StatusOperacije.PLANIRANA);
        if(sledeca != null){
            sledeca=postaviAktuelnu(sledeca.getId());
        }
        else {
            throw new BackEndError("Nema planirane operacije za zaposlenog sa id " + zaposleniId);
        }

        return sledeca;
    }

    public RadnikOperacijaQueue getAktuelnaOperacija(Long zaposleniId) {

        return radnikOperacijaQueueRepo.findFirstByZaposleniIdAndStatusOperacije(zaposleniId, StatusOperacije.AKTUELNA);
    }

    public RadnikOperacijaQueue getPlaniranaOperacija(Long zaposleniId) {
        return radnikOperacijaQueueRepo.findFirstByZaposleniIdAndStatusOperacije(zaposleniId, StatusOperacije.PLANIRANA);
    }

    public List<RadnikOperacijaQueue> getPlaniraneOperacije(Long zaposleniId) {
        return radnikOperacijaQueueRepo.findAllByZaposleniIdAndStatusOperacije(zaposleniId, StatusOperacije.PLANIRANA);
    }
}
