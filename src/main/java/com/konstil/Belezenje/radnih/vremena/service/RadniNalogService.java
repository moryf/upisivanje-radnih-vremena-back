package com.konstil.Belezenje.radnih.vremena.service;

import com.konstil.Belezenje.radnih.vremena.domain.RadniNalog;
import com.konstil.Belezenje.radnih.vremena.domain.StanjeNaloga;
import com.konstil.Belezenje.radnih.vremena.repository.RadniNalogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class RadniNalogService {
    RadniNalogRepo radniNalogRepo;

    @Autowired
    public RadniNalogService(RadniNalogRepo radniNalogRepo) {
        this.radniNalogRepo = radniNalogRepo;
    }


    public List<RadniNalog> findAktivniNalozi() {
        return radniNalogRepo.findByStanje(StanjeNaloga.ACTIVE);
    }



}
