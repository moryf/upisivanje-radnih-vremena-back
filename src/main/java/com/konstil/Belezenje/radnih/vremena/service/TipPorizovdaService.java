package com.konstil.Belezenje.radnih.vremena.service;

import com.konstil.Belezenje.radnih.vremena.domain.TipProizvoda;
import com.konstil.Belezenje.radnih.vremena.dto.TipProizvodaDTO;
import com.konstil.Belezenje.radnih.vremena.repository.ProizvodRepo;
import com.konstil.Belezenje.radnih.vremena.repository.TipProizvodaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TipPorizovdaService {
    private TipProizvodaRepo tipProizvodaRepo;
    private ProizvodRepo proizvodRepo;

    @Autowired
    public TipPorizovdaService(TipProizvodaRepo tipProizvodaRepo, ProizvodRepo proizvodRepo) {
        this.tipProizvodaRepo = tipProizvodaRepo;
        this.proizvodRepo = proizvodRepo;
    }

    public List<TipProizvodaDTO> getallSaProizvodima() {
        List<TipProizvoda> tipProizvodaList = tipProizvodaRepo.findAll();
        List<TipProizvodaDTO> tipProizvodaDTOList = new ArrayList<>();
        for(TipProizvoda tipProizvoda : tipProizvodaList) {
            TipProizvodaDTO tipProizvodaDTO = new TipProizvodaDTO();
            tipProizvodaDTO.setTipProizvoda(tipProizvoda);
            tipProizvodaDTO.setProizvodi(proizvodRepo.findByTipProizvoda(tipProizvoda));
            tipProizvodaDTOList.add(tipProizvodaDTO);
        }
        return tipProizvodaDTOList;
    }

    public TipProizvoda addTipProizvoda(String naziv) {
        TipProizvoda tipProizvoda = new TipProizvoda();
        tipProizvoda.setNaziv(naziv);
        return tipProizvodaRepo.save(tipProizvoda);
    }

    public List<TipProizvoda> getall() {
        return tipProizvodaRepo.findAll();
    }
}
