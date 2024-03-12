package com.konstil.Belezenje.radnih.vremena.service;

import com.konstil.Belezenje.radnih.vremena.domain.Operacija;
import com.konstil.Belezenje.radnih.vremena.repository.OperacijaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperacijaService {
    @Autowired
    OperacijaRepo operacijaRepo;
    public List<Operacija> getAllOperacija() {
        return operacijaRepo.findAll();
    }
}
