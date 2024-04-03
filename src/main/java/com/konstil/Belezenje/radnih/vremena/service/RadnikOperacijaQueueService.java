package com.konstil.Belezenje.radnih.vremena.service;

import com.konstil.Belezenje.radnih.vremena.domain.RadnikOperacija;
import com.konstil.Belezenje.radnih.vremena.domain.RadnikOperacijaQueue;
import com.konstil.Belezenje.radnih.vremena.domain.StatusOperacije;
import com.konstil.Belezenje.radnih.vremena.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Service
public class RadnikOperacijaQueueService {
    RadnikOperacijaQueueRepo radnikOperacijaQueueRepo;
    ZaposleniRepo zaposleniRepo;
    OperacijaRepo operacijaRepo;
    RadniNalogRepo radniNalogRepo;
    RadnikOperacijaRepo radnikOperacijaRepo;

    @Autowired
    public RadnikOperacijaQueueService(RadnikOperacijaQueueRepo radnikOperacijaQueueRepo, ZaposleniRepo zaposleniRepo, OperacijaRepo operacijaRepo, RadniNalogRepo radniNalogRepo, RadnikOperacijaRepo radnikOperacijaRepo) {
        this.radnikOperacijaQueueRepo = radnikOperacijaQueueRepo;
        this.zaposleniRepo = zaposleniRepo;
        this.operacijaRepo = operacijaRepo;
        this.radniNalogRepo = radniNalogRepo;
        this.radnikOperacijaRepo = radnikOperacijaRepo;
    }





    public RadnikOperacijaQueue postPlaniranaOperacijaZaposleni(Integer zaposleniId, Integer operacijaId, String radniNalogSifra) {
        RadnikOperacijaQueue radnikOperacijaQueue = new RadnikOperacijaQueue();
        radnikOperacijaQueue.setStatusOperacije(StatusOperacije.PLANIRANA);
        radnikOperacijaQueue.setOperacija(operacijaRepo.findById(operacijaId).get());
        radnikOperacijaQueue.setZaposleni(zaposleniRepo.findById(zaposleniId).get());
        radnikOperacijaQueue.setRadniNalog(radniNalogRepo.findBySifra(radniNalogSifra));
        int maxRedosled = radnikOperacijaQueueRepo.findMaxRedosledByZaposleniId(zaposleniId);
        if (maxRedosled==0) {
            radnikOperacijaQueue.setRedosled(1);
        } else {
            radnikOperacijaQueue.setRedosled(maxRedosled+1);
        }

        System.out.println(radnikOperacijaQueue);

        return radnikOperacijaQueueRepo.save(radnikOperacijaQueue);
    }

    public RadnikOperacijaQueue putRadnikoperacijaQueueAktuelna(Long id, Integer zaposleniId) {
        RadnikOperacijaQueue radnikOperacijaQueue = radnikOperacijaQueueRepo.findById(id).get();
        radnikOperacijaQueue.setPocetak(new Date());
        radnikOperacijaQueue.setStatusOperacije(StatusOperacije.AKTUELNA);
        if (radnikOperacijaQueue.getZaposleni()==null) {
            radnikOperacijaQueue.setZaposleni(zaposleniRepo.findById(zaposleniId).get());
        }
        return radnikOperacijaQueueRepo.save(radnikOperacijaQueue);
    }

    public RadnikOperacija putRadnikoperacijaQueueZavrsena(Long id) {
        RadnikOperacija radnikOperacija= new RadnikOperacija();
        RadnikOperacijaQueue radnikOperacijaQueue = radnikOperacijaQueueRepo.findById(id).get();
        radnikOperacija.setDatum(new Date());
        radnikOperacija.setPocetak(new Time(radnikOperacijaQueue.getPocetak().getTime()));
        radnikOperacija.setKraj(new Time(new Date().getTime()));
        radnikOperacija.setOperacija(radnikOperacijaQueue.getOperacija());
        radnikOperacija.setZaposleni(radnikOperacijaQueue.getZaposleni());
        radnikOperacija.setRadniNalog(radnikOperacijaQueue.getRadniNalog());
        radnikOperacija.setOpisPosla(radnikOperacijaQueue.getOperacija().getNaziv());

        RadnikOperacijaQueue sledeca = new RadnikOperacijaQueue();

        radnikOperacijaQueueRepo.deleteById(id);
        return radnikOperacijaRepo.save(radnikOperacija);
    }

    public RadnikOperacijaQueue getAktuelnaOperacija(Integer id) {
        return radnikOperacijaQueueRepo.findByZaposleniIdAndAndStatusOperacije(id,StatusOperacije.AKTUELNA);
    }


    public RadnikOperacijaQueue getPlaniranaOperacija(Integer zaposleniId) {
        return radnikOperacijaQueueRepo.findFirstByZaposleniIdAndStatusOperacije(zaposleniId,StatusOperacije.PLANIRANA);
    }


    public List<RadnikOperacijaQueue> getPlaniraneOperacije(Integer zaposleniId) {
        return radnikOperacijaQueueRepo.findAllByZaposleniIdAndStatusOperacijeOrderByRadniNalogRokAsc(zaposleniId,StatusOperacije.PLANIRANA);
    }

    public RadnikOperacijaQueue zavrsiAktuelnuZapocniSledecu(Integer zaposleniId) {
        RadnikOperacijaQueue aktuelna = getAktuelnaOperacija(zaposleniId);
        RadnikOperacijaQueue sledeca = getPlaniranaOperacija(zaposleniId);
        putRadnikoperacijaQueueZavrsena(aktuelna.getId());
        sledeca = putRadnikoperacijaQueueAktuelna(sledeca.getId(), zaposleniId);
        return sledeca;
    }

    public String deleteRadnikOperacijaQueue(Long id) {
        radnikOperacijaQueueRepo.deleteById(id);
        return "Uspesno obrisana";
    }

    public RadnikOperacijaQueue pauzirajAktuelnu(Integer zaposleniId) {
        RadnikOperacijaQueue aktuelna = getAktuelnaOperacija(zaposleniId);
        RadnikOperacija radnikOperacija = new RadnikOperacija();
        radnikOperacija.setDatum(new Date());
        radnikOperacija.setPocetak(new Time(aktuelna.getPocetak().getTime()));
        radnikOperacija.setOperacija(aktuelna.getOperacija());
        radnikOperacija.setRadniNalog(aktuelna.getRadniNalog());
        radnikOperacija.setZaposleni(aktuelna.getZaposleni());
        radnikOperacija.setKraj(new Time(new Date().getTime()));
        radnikOperacija.setOpisPosla(aktuelna.getOperacija().getNaziv());
        radnikOperacijaRepo.save(radnikOperacija);
        aktuelna.setStatusOperacije(StatusOperacije.PLANIRANA);
        return radnikOperacijaQueueRepo.save(aktuelna);
    }

    public RadnikOperacijaQueue zameniAktuelnuOperaciju(Integer zaposleniId, Integer operacijaId, String radniNalogSifra) {
        RadnikOperacijaQueue nova = postPlaniranaOperacijaZaposleni(zaposleniId,operacijaId,radniNalogSifra);
        pauzirajAktuelnu(zaposleniId);
        putRadnikoperacijaQueueAktuelna(nova.getId(), zaposleniId);
        return nova;
    }
}
