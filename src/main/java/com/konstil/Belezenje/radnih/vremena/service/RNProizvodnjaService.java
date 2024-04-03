package com.konstil.Belezenje.radnih.vremena.service;

import com.konstil.Belezenje.radnih.vremena.controller.ZaposleniController;
import com.konstil.Belezenje.radnih.vremena.domain.RNProizvodnja;
import com.konstil.Belezenje.radnih.vremena.domain.RadniNalog;
import com.konstil.Belezenje.radnih.vremena.repository.ProizvodRepo;
import com.konstil.Belezenje.radnih.vremena.repository.RNProizvodnjaRepo;
import com.konstil.Belezenje.radnih.vremena.repository.RadniNalogRepo;
import com.konstil.Belezenje.radnih.vremena.repository.ZaposleniRepo;
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

    @Autowired
    public RNProizvodnjaService(RNProizvodnjaRepo rnProizvodnjaRepo, RadniNalogRepo radniNalogRepo, ProizvodRepo proizvodRepo, ZaposleniRepo zaposleniRepo) {
        this.rnProizvodnjaRepo = rnProizvodnjaRepo;
        this.radniNalogRepo = radniNalogRepo;
        this.proizvodRepo = proizvodRepo;
        this.zaposleniRepo = zaposleniRepo;
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
