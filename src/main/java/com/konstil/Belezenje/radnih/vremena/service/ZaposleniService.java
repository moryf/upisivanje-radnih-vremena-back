package com.konstil.Belezenje.radnih.vremena.service;

import com.konstil.Belezenje.radnih.vremena.domain.Zaposleni;
import com.konstil.Belezenje.radnih.vremena.exception.BackEndError;
import com.konstil.Belezenje.radnih.vremena.repository.ZaposleniRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZaposleniService {
    ZaposleniRepo zaposleniRepo;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public ZaposleniService(ZaposleniRepo zaposleniRepo) {
        this.zaposleniRepo = zaposleniRepo;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public Zaposleni login(String korisnickoIme, String lozinka) {
        Zaposleni zaposleni = zaposleniRepo.findByKorisnickoIme(korisnickoIme);
        if (zaposleni == null) {
            throw new BackEndError("Korisnicko ime ne postoji");
        }
        if (!bCryptPasswordEncoder.matches(lozinka, zaposleni.getLozinka())) {
            throw new BackEndError("Pogresna lozinka");
        }
        return zaposleni;
    }

    public Zaposleni register(Zaposleni zaposleni) {
        if (zaposleniRepo.findByKorisnickoIme(zaposleni.getKorisnickoIme()) != null) {
            throw new BackEndError("Korisnicko ime vec postoji");
        }
        zaposleni.setLozinka(bCryptPasswordEncoder.encode(zaposleni.getLozinka()));
        return zaposleniRepo.save(zaposleni);
    }

    public List<Zaposleni> getAllZaposleni() {
        return zaposleniRepo.findAll();
    }
}
