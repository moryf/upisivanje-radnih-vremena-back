package com.konstil.Belezenje.radnih.vremena.service;

import com.konstil.Belezenje.radnih.vremena.domain.RadniNalog;
import com.konstil.Belezenje.radnih.vremena.repository.RadniNalogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RadniNalogService {
    @Autowired
    RadniNalogRepo radniNalogRepo;
    public List<RadniNalog> getAllRadniNalog() {
        return radniNalogRepo.findAll();
    }
}
