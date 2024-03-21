package com.konstil.Belezenje.radnih.vremena.service;

import com.konstil.Belezenje.radnih.vremena.domain.RadnikOperacija;
import com.konstil.Belezenje.radnih.vremena.domain.StanjeNaloga;
import com.konstil.Belezenje.radnih.vremena.repository.RadnikOperacijaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RadnikOperacijaService {
    RadnikOperacijaRepo radnikOperacijaRepo;

    @Autowired
    public RadnikOperacijaService(RadnikOperacijaRepo radnikOperacijaRepo) {
        this.radnikOperacijaRepo = radnikOperacijaRepo;
    }

    public List<RadnikOperacija> findAllActiveByZaposleniId(Integer id){
        return radnikOperacijaRepo.findAllByZaposleniIdAndRadniNalogStanje(id, StanjeNaloga.ACTIVE);
    }
}
