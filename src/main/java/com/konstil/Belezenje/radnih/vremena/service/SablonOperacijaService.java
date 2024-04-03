package com.konstil.Belezenje.radnih.vremena.service;

import com.konstil.Belezenje.radnih.vremena.domain.SablonOperacija;
import com.konstil.Belezenje.radnih.vremena.domain.TipProizvoda;
import com.konstil.Belezenje.radnih.vremena.repository.OperacijaRepo;
import com.konstil.Belezenje.radnih.vremena.repository.SablonOperacijaRepo;
import com.konstil.Belezenje.radnih.vremena.repository.TipProizvodaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SablonOperacijaService {
    private SablonOperacijaRepo sablonOperacijaRepo;
    private TipProizvodaRepo tipProizvodaRepo;
    private OperacijaRepo operacijaRepo;

    @Autowired
    public SablonOperacijaService (SablonOperacijaRepo sablonOperacijaRepo, TipProizvodaRepo tipProizvodaRepo, OperacijaRepo operacijaRepo){
        this.sablonOperacijaRepo = sablonOperacijaRepo;
        this.tipProizvodaRepo = tipProizvodaRepo;
        this.operacijaRepo = operacijaRepo;
    }


    public SablonOperacija addSablonOperacija(Integer tipProizvodaId, Integer operacijaId) {
        SablonOperacija sablonOperacija = new SablonOperacija();
        sablonOperacija.setTipProizvoda(tipProizvodaRepo.findById(tipProizvodaId).get());
        sablonOperacija.setOperacija(operacijaRepo.findById(operacijaId).get());
        int redosled = sablonOperacijaRepo.findByTipProizvodaOrderByRedosledAsc(sablonOperacija.getTipProizvoda()).size() + 1;
        sablonOperacija.setRedosled(redosled);
        return sablonOperacijaRepo.save(sablonOperacija);
    }


    public List<SablonOperacija> saveAll(List<SablonOperacija> sablonOperacijaList) {
        return sablonOperacijaRepo.saveAll(sablonOperacijaList);
    }

    public void deleteSablonOperacija(Integer id) {
        TipProizvoda tipProizvoda = sablonOperacijaRepo.findById(id).get().getTipProizvoda();
        int redosled = sablonOperacijaRepo.findById(id).get().getRedosled();
        sablonOperacijaRepo.deleteById(id);
        List<SablonOperacija> sablonOperacijaList = sablonOperacijaRepo.findByTipProizvodaOrderByRedosledAsc(tipProizvoda);
        for(int i = 0; i < sablonOperacijaList.size(); i++) {
            sablonOperacijaList.get(i).setRedosled(i+1);
            sablonOperacijaRepo.save(sablonOperacijaList.get(i));
        }
    }
}
