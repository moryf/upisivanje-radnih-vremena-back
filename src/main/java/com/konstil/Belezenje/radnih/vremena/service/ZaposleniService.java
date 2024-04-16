package com.konstil.Belezenje.radnih.vremena.service;

import com.konstil.Belezenje.radnih.vremena.domain.Operacija;
import com.konstil.Belezenje.radnih.vremena.domain.StatusOperacije;
import com.konstil.Belezenje.radnih.vremena.domain.Uloga;
import com.konstil.Belezenje.radnih.vremena.domain.Zaposleni;
import com.konstil.Belezenje.radnih.vremena.dto.LoginRequest;
import com.konstil.Belezenje.radnih.vremena.dto.ZaposleniAktuelnaPlaniranaDTO;
import com.konstil.Belezenje.radnih.vremena.exception.BackEndError;
import com.konstil.Belezenje.radnih.vremena.repository.OperacijaRepo;
import com.konstil.Belezenje.radnih.vremena.repository.RadnikOperacijaQueueRepo;
import com.konstil.Belezenje.radnih.vremena.repository.ZaposleniRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZaposleniService {

    ZaposleniRepo zaposleniRepo;

    BCryptPasswordEncoder bCryptPasswordEncoder;

    RadnikOperacijaQueueRepo radnikOperacijaQueueRepo;

    OperacijaRepo operacijaRepo;

    @Autowired
    public ZaposleniService(ZaposleniRepo zaposleniRepo, RadnikOperacijaQueueRepo radnikOperacijaQueueRepo, OperacijaRepo operacijaRepo) {
        this.zaposleniRepo = zaposleniRepo;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.radnikOperacijaQueueRepo = radnikOperacijaQueueRepo;
        this.operacijaRepo = operacijaRepo;
    }


    public Zaposleni login(LoginRequest loginRequest) {
        System.out.println(loginRequest.getKorisnickoIme());
        Zaposleni zaposleni = zaposleniRepo.findByKorisnickoIme(loginRequest.getKorisnickoIme());
        if(zaposleni==null)
            throw new BackEndError("Korisnicko ime ne postoji");
        if(bCryptPasswordEncoder.matches(loginRequest.getLozinka(),zaposleni.getLozinka()))
            return zaposleni;
        else
            throw new BackEndError("Pogresna lozinka");

    }

    public Zaposleni register(Zaposleni zaposleni) {
        zaposleni.setLozinka(bCryptPasswordEncoder.encode(zaposleni.getLozinka()));
        return zaposleniRepo.save(zaposleni);
    }

    public List<ZaposleniAktuelnaPlaniranaDTO> getSviAktivni() {

        List<Zaposleni> zaposleniList =  zaposleniRepo.findAllByActiveOrderByImeAsc(Zaposleni.Active.DA);
        List<ZaposleniAktuelnaPlaniranaDTO> dtoList = new ArrayList<>();
        for (Zaposleni zaposleni :zaposleniList){
            ZaposleniAktuelnaPlaniranaDTO dto = new ZaposleniAktuelnaPlaniranaDTO();
            dto.setAktuelna(radnikOperacijaQueueRepo.findFirstByZaposleniIdAndStatusOperacijeOrderByRedosledAsc(zaposleni.getId(), StatusOperacije.AKTUELNA));
            dto.setPlanirana(radnikOperacijaQueueRepo.findFirstByZaposleniIdAndStatusOperacijeOrderByRedosledAsc(zaposleni.getId(),StatusOperacije.PLANIRANA));
            dto.setZaposleni(zaposleni);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public Zaposleni getZaposleniById(Integer id) {
        return zaposleniRepo.findById(id).get();
    }

    public Zaposleni cardLogin(String cardId) {
        Zaposleni zaposleni = zaposleniRepo.findById(Integer.parseInt(cardId)).get();
        if(zaposleni==null)
            throw new BackEndError("Korisnicko ime ne postoji");
        return zaposleni;
    }

    public List<Zaposleni> getMenadzeri() {
        return zaposleniRepo.findAllByActiveAndUloga(Zaposleni.Active.DA,Uloga.MENADZER);
    }

    public List<Zaposleni> getAll() {
        return zaposleniRepo.findAllByActiveOrderByImeAsc(Zaposleni.Active.DA);
    }

    public Zaposleni addPodredjeni(Integer id, Integer podredjeniId) {
        Zaposleni menadzer = zaposleniRepo.findById(id).get();
        Zaposleni podredjeni = zaposleniRepo.findById(podredjeniId).get();
        menadzer.getPodredjeni().add(podredjeni);
        return zaposleniRepo.save(menadzer);
    }

    public Zaposleni addKvalifikacija(Integer id, Integer operacijaId) {
        Zaposleni zaposleni = zaposleniRepo.findById(id).get();
        Operacija operacija = operacijaRepo.findById(operacijaId).get();
        zaposleni.getKvalifikacije().add(operacija);
        return zaposleniRepo.save(zaposleni);
    }

    public Zaposleni removePodredjeni(Integer id, Integer podredjeniId) {
        Zaposleni menadzer = zaposleniRepo.findById(id).get();
        Zaposleni podredjeni = zaposleniRepo.findById(podredjeniId).get();
        menadzer.getPodredjeni().remove(podredjeni);
        return zaposleniRepo.save(menadzer);
    }

    public Zaposleni removeKvalifikacija(Integer id, Integer operacijaId) {
        Zaposleni zaposleni = zaposleniRepo.findById(id).get();
        Operacija operacija = operacijaRepo.findById(operacijaId).get();
        zaposleni.getKvalifikacije().remove(operacija);
        return zaposleniRepo.save(zaposleni);
    }

    public Zaposleni updateZaposleni( Zaposleni zaposleni) {
        System.out.println(zaposleni);
        return zaposleniRepo.save(zaposleni);
    }
}
