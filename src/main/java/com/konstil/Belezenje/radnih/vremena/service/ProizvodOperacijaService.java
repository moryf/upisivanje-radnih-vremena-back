package com.konstil.Belezenje.radnih.vremena.service;

import com.konstil.Belezenje.radnih.vremena.domain.ProizvodOperacija;
import com.konstil.Belezenje.radnih.vremena.domain.SablonOperacija;
import com.konstil.Belezenje.radnih.vremena.repository.ProizvodOperacijaRepo;
import com.konstil.Belezenje.radnih.vremena.repository.ProizvodRepo;
import com.konstil.Belezenje.radnih.vremena.repository.SablonOperacijaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProizvodOperacijaService {
    private ProizvodOperacijaRepo proizvodOperacijaRepo;
    private SablonOperacijaRepo sablonOperacijaRepo;
    private ProizvodRepo proizvodRepo;

    @Autowired
    public ProizvodOperacijaService(ProizvodOperacijaRepo proizvodOperacijaRepo, SablonOperacijaRepo sablonOperacijaRepo, ProizvodRepo proizvodRepo) {
        this.proizvodOperacijaRepo = proizvodOperacijaRepo;
        this.sablonOperacijaRepo = sablonOperacijaRepo;
        this.proizvodRepo = proizvodRepo;
    }

    public List<ProizvodOperacija> getProizvodOperacijaByProizvodId(Integer proizvodId) {
        return proizvodOperacijaRepo.findByProizvodId(proizvodId);
    }

    public List<ProizvodOperacija> uveziIzSablona(Integer proizvodId) {
        List<ProizvodOperacija> proizvodOperacijas = proizvodOperacijaRepo.findByProizvodId(proizvodId);
        proizvodOperacijaRepo.deleteAll(proizvodOperacijas);
        List<SablonOperacija> sablonOperacijas = sablonOperacijaRepo.findByTipProizvodaOrderByRedosledAsc(proizvodRepo.findById(proizvodId).get().getTipProizvoda());
        for (SablonOperacija sablonOperacija : sablonOperacijas) {
            ProizvodOperacija proizvodOperacija = new ProizvodOperacija();
            proizvodOperacija.setOperacija(sablonOperacija.getOperacija());
            proizvodOperacija.setProizvod(proizvodRepo.findById(proizvodId).get());
            proizvodOperacija.setRedosled(sablonOperacija.getRedosled());
            proizvodOperacijaRepo.save(proizvodOperacija);
        }
        return proizvodOperacijaRepo.findByProizvodId(proizvodId);
    }

    public List<ProizvodOperacija> saveAll(List<ProizvodOperacija> proizvodOperacijaList) {
        List<ProizvodOperacija> izBazeZaProizvod = proizvodOperacijaRepo.findByProizvodId(proizvodOperacijaList.get(0).getProizvod().getId());
        proizvodOperacijaRepo.deleteAll(izBazeZaProizvod);
        return proizvodOperacijaRepo.saveAll(proizvodOperacijaList);
    }
}
