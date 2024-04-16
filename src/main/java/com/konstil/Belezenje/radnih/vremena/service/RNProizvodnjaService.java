package com.konstil.Belezenje.radnih.vremena.service;

import com.konstil.Belezenje.radnih.vremena.controller.ZaposleniController;
import com.konstil.Belezenje.radnih.vremena.domain.RNProizvodnja;
import com.konstil.Belezenje.radnih.vremena.domain.RadniNalog;
import com.konstil.Belezenje.radnih.vremena.domain.RadnikOperacijaQueue;
import com.konstil.Belezenje.radnih.vremena.domain.StatusOperacije;
import com.konstil.Belezenje.radnih.vremena.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RNProizvodnjaService {
    RNProizvodnjaRepo rnProizvodnjaRepo;
    RadniNalogRepo radniNalogRepo;
    ProizvodRepo proizvodRepo;
    ZaposleniRepo zaposleniRepo;
    RadnikOperacijaQueueRepo radnikOperacijaQueueRepo;
    ProizvodOperacijaRepo proizvodOperacijaRepo;

    @Autowired
    public RNProizvodnjaService(RNProizvodnjaRepo rnProizvodnjaRepo, RadniNalogRepo radniNalogRepo, ProizvodRepo proizvodRepo, ZaposleniRepo zaposleniRepo, RadnikOperacijaQueueRepo radnikOperacijaQueueRepo, ProizvodOperacijaRepo proizvodOperacijaRepo) {
        this.rnProizvodnjaRepo = rnProizvodnjaRepo;
        this.radniNalogRepo = radniNalogRepo;
        this.proizvodRepo = proizvodRepo;
        this.zaposleniRepo = zaposleniRepo;
        this.radnikOperacijaQueueRepo = radnikOperacijaQueueRepo;

        this.proizvodOperacijaRepo = proizvodOperacijaRepo;
    }

    public List<RNProizvodnja> getAll() {
        return rnProizvodnjaRepo.findAll();
    }

    public RNProizvodnja dodajRNuProizvodnju(Integer id, String tipRN, Integer proizvodId, Integer nadlezniId) {
        RNProizvodnja rnProizvodnja = new RNProizvodnja();
        rnProizvodnja.setNadlezni(zaposleniRepo.findById(nadlezniId).orElse(null));
        rnProizvodnja.setRadniNalog(radniNalogRepo.findById(id).orElse(null));
        rnProizvodnja.setTip(RNProizvodnja.Tip.valueOf(tipRN));
        rnProizvodnja.setStatus(RNProizvodnja.Status.U_TOKU);
        rnProizvodnja.setOtvoren(new Date());
        if (proizvodId==0){
            rnProizvodnja.setProizvod(null);
        } else {
            rnProizvodnja.setProizvod(proizvodRepo.findById(proizvodId).orElse(null));
        }
        if(rnProizvodnja.getTip()== RNProizvodnja.Tip.STANDARD){
            RadnikOperacijaQueue radnikOperacijaQueue = new RadnikOperacijaQueue();
            radnikOperacijaQueue.setStatusOperacije(StatusOperacije.PLANIRANA);
            radnikOperacijaQueue.setRadniNalog(rnProizvodnja.getRadniNalog());
            radnikOperacijaQueue.setOperacija(proizvodOperacijaRepo.findByProizvodIdAndRedosled(rnProizvodnja.getProizvod().getId(), 1).get().getOperacija());
            radnikOperacijaQueue.setMasina(proizvodOperacijaRepo.findByProizvodIdAndRedosled(rnProizvodnja.getProizvod().getId(), 1).get().getMasina());
            radnikOperacijaQueue.setRedosled(1);
            radnikOperacijaQueueRepo.save(radnikOperacijaQueue);
        }
        return rnProizvodnjaRepo.save(rnProizvodnja);
    }

    public List<RadniNalog> getRNProizvodnjaNadredjeni(Integer id) {
        List<RNProizvodnja> rnProizvodnjaList = rnProizvodnjaRepo.findAllByNadlezniId(id);

        List<RadniNalog> radniNalogList = new ArrayList<>();
        for (RNProizvodnja rnProizvodnja : rnProizvodnjaList) {
            radniNalogList.add(rnProizvodnja.getRadniNalog());
        }
        return radniNalogList;
    }
}
