package com.konstil.Belezenje.radnih.vremena.service;

import com.konstil.Belezenje.radnih.vremena.domain.*;
import com.konstil.Belezenje.radnih.vremena.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import static com.konstil.Belezenje.radnih.vremena.domain.StatusOperacije.PLANIRANA;

@Service
public class RadnikOperacijaQueueService {
    RadnikOperacijaQueueRepo radnikOperacijaQueueRepo;
    ZaposleniRepo zaposleniRepo;
    OperacijaRepo operacijaRepo;
    RadniNalogRepo radniNalogRepo;
    RadnikOperacijaRepo radnikOperacijaRepo;
    RNProizvodnjaRepo rnProizvodnjaRepo;
    ProizvodOperacijaRepo proizvodOperacijaRepo;

    @Autowired
    public RadnikOperacijaQueueService(RadnikOperacijaQueueRepo radnikOperacijaQueueRepo, ZaposleniRepo zaposleniRepo, OperacijaRepo operacijaRepo, RadniNalogRepo radniNalogRepo, RadnikOperacijaRepo radnikOperacijaRepo, RNProizvodnjaRepo rnProizvodnjaRepo, ProizvodOperacijaRepo proizvodOperacijaRepo) {
        this.radnikOperacijaQueueRepo = radnikOperacijaQueueRepo;
        this.zaposleniRepo = zaposleniRepo;
        this.operacijaRepo = operacijaRepo;
        this.radniNalogRepo = radniNalogRepo;
        this.radnikOperacijaRepo = radnikOperacijaRepo;
        this.rnProizvodnjaRepo = rnProizvodnjaRepo;
        this.proizvodOperacijaRepo = proizvodOperacijaRepo;
    }


    public RadnikOperacijaQueue postPlaniranaOperacijaZaposleni(Integer zaposleniId, Integer operacijaId, String radniNalogSifra) {
        RadnikOperacijaQueue radnikOperacijaQueue = new RadnikOperacijaQueue();
        radnikOperacijaQueue.setStatusOperacije(PLANIRANA);
        radnikOperacijaQueue.setOperacija(operacijaRepo.findById(operacijaId).get());
        radnikOperacijaQueue.setZaposleni(zaposleniRepo.findById(zaposleniId).get());
        radnikOperacijaQueue.setRadniNalog(rnProizvodnjaRepo.findByRadniNalogId(radniNalogRepo.findBySifra(radniNalogSifra).getId()));
        int maxRedosled = radnikOperacijaQueueRepo.findMaxRedosledByZaposleniId(zaposleniId).orElse(0);
        if (maxRedosled==0) {
            radnikOperacijaQueue.setRedosled(1);
        } else {
            radnikOperacijaQueue.setRedosled(maxRedosled+1);
        }

        radnikOperacijaQueue = radnikOperacijaQueueRepo.save(radnikOperacijaQueue);
        reorderRadnikOperacijaQueue(zaposleniId);

        return radnikOperacijaQueue;
    }

    public RadnikOperacijaQueue putRadnikoperacijaQueueAktuelna(Long id, Integer zaposleniId) {
        RadnikOperacijaQueue radnikOperacijaQueue = radnikOperacijaQueueRepo.findById(id).get();
        radnikOperacijaQueue.setPocetak(new Date());
        radnikOperacijaQueue.setStatusOperacije(StatusOperacije.AKTUELNA);
        if (radnikOperacijaQueue.getZaposleni()==null) {
            radnikOperacijaQueue.setZaposleni(zaposleniRepo.findById(zaposleniId).get());
        }
        reorderRadnikOperacijaQueue(zaposleniId);
        return radnikOperacijaQueueRepo.save(radnikOperacijaQueue);
    }

    public RadnikOperacija putRadnikoperacijaQueueZavrsena(Long id) {
        RadnikOperacija radnikOperacija= new RadnikOperacija();
        RadnikOperacijaQueue radnikOperacijaQueue = radnikOperacijaQueueRepo.findById(id).get();
        radnikOperacija.setDatum(new Date());
        radnikOperacija.setPocetak(new Time(radnikOperacijaQueue.getPocetak().getTime()));
        radnikOperacija.setKraj(new Time(new Date().getTime()));
        radnikOperacija.setOperacija(radnikOperacijaQueue.getOperacija());
        radnikOperacija.setZaposleni(radnikOperacijaQueue.getZaposleni());
        radnikOperacija.setRadniNalog(radnikOperacijaQueue.getRadniNalog().getRadniNalog());
        radnikOperacija.setOpisPosla(radnikOperacijaQueue.getOperacija().getNaziv());

        RNProizvodnja rnProizvodnja = radnikOperacijaQueue.getRadniNalog();

        if(rnProizvodnja.getTip()== RNProizvodnja.Tip.STANDARD){
            ProizvodOperacija sledeciProizvodOperacija = proizvodOperacijaRepo.findByProizvodIdAndRedosled(rnProizvodnja.getProizvod().getId(), radnikOperacijaQueue.getRedosled()+1).orElse(null);
            if (sledeciProizvodOperacija==null){
                rnProizvodnja.setStatus(RNProizvodnja.Status.ZAVRSEN);
                rnProizvodnjaRepo.save(rnProizvodnja);

            }else {
                RadnikOperacijaQueue sledeca = postPlaniranaOperacijaStandard(sledeciProizvodOperacija.getOperacija().getId(),sledeciProizvodOperacija.getRedosled(), radnikOperacijaQueue.getRadniNalog().getRadniNalog().getSifra());
                sledeca.setMasina(sledeciProizvodOperacija.getMasina());
                radnikOperacijaQueueRepo.save(sledeca);
            }
        }
        reorderRadnikOperacijaQueue(radnikOperacijaQueue.getZaposleni().getId());
        radnikOperacijaQueueRepo.deleteById(id);

        return radnikOperacijaRepo.save(radnikOperacija);
    }

    private RadnikOperacijaQueue postPlaniranaOperacijaStandard(Integer id, Integer redosled, String sifra) {
        RadnikOperacijaQueue radnikOperacijaQueue = new RadnikOperacijaQueue();
        radnikOperacijaQueue.setStatusOperacije(PLANIRANA);
        radnikOperacijaQueue.setOperacija(operacijaRepo.findById(id).get());
        radnikOperacijaQueue.setRadniNalog(rnProizvodnjaRepo.findByRadniNalogId(radniNalogRepo.findBySifra(sifra).getId()));
        radnikOperacijaQueue.setRedosled(redosled);
        return radnikOperacijaQueueRepo.save(radnikOperacijaQueue);
    }


    public RadnikOperacijaQueue getAktuelnaOperacija(Integer id) {
        return radnikOperacijaQueueRepo.findByZaposleniIdAndAndStatusOperacije(id,StatusOperacije.AKTUELNA);
    }


    public RadnikOperacijaQueue getPlaniranaOperacija(Integer zaposleniId) {
        Zaposleni zaposleni = zaposleniRepo.findById(zaposleniId).get();
        if(zaposleni.getRezim()== Zaposleni.Rezim.KONSTRUKCIJE) {
            return radnikOperacijaQueueRepo.findFirstByZaposleniIdAndStatusOperacijeOrderByRedosledAsc(zaposleniId, PLANIRANA);
        }
        List<RadnikOperacijaQueue> planirane= radnikOperacijaQueueRepo.findAllByStatusOperacijeOrderByRadniNalogRokAscRedosledAsc(PLANIRANA);
        for (RadnikOperacijaQueue radnikOperacijaQueue : planirane) {
            if ((radnikOperacijaQueue.getZaposleni()==null || radnikOperacijaQueue.getZaposleni().getId().equals(zaposleniId)) && zaposleni.getKvalifikacije().contains(radnikOperacijaQueue.getOperacija())) {
                return radnikOperacijaQueue;
            }
        }
        return null;
    }


    public List<RadnikOperacijaQueue> getPlaniraneOperacije(Integer zaposleniId) {
        Zaposleni zaposleni = zaposleniRepo.findById(zaposleniId).get();
        if (zaposleni.getRezim()== Zaposleni.Rezim.KONSTRUKCIJE) {
            return radnikOperacijaQueueRepo.findAllByZaposleniIdAndStatusOperacijeOrderByRedosledAsc(zaposleniId, PLANIRANA);
        }
        else{
            List<RadnikOperacijaQueue> planirane= radnikOperacijaQueueRepo.findAllByStatusOperacijeOrderByRadniNalogRokAscRedosledAsc(PLANIRANA);
            for (int i=0; i<planirane.size(); i++) {
                RadnikOperacijaQueue radnikOperacijaQueue = planirane.get(i);
                System.out.println((radnikOperacijaQueue.getZaposleni()==null||radnikOperacijaQueue.getZaposleni().getId().equals(zaposleniId)) && zaposleni.getKvalifikacije().contains(radnikOperacijaQueue.getOperacija()));
                if (!((radnikOperacijaQueue.getZaposleni()==null||radnikOperacijaQueue.getZaposleni().getId().equals(zaposleniId)) && zaposleni.getKvalifikacije().contains(radnikOperacijaQueue.getOperacija()))) {
                    planirane.remove(radnikOperacijaQueue);
                    i--;
                }
            }
            return planirane;
        }


    }

    public RadnikOperacijaQueue zavrsiAktuelnuZapocniSledecu(Integer zaposleniId) {
        RadnikOperacijaQueue aktuelna = getAktuelnaOperacija(zaposleniId);
        putRadnikoperacijaQueueZavrsena(aktuelna.getId());
        RadnikOperacijaQueue sledeca = getPlaniranaOperacija(zaposleniId);
        sledeca = putRadnikoperacijaQueueAktuelna(sledeca.getId(), zaposleniId);
        reorderRadnikOperacijaQueue(zaposleniId);
        return sledeca;
    }

    public String deleteRadnikOperacijaQueue(Long id) {
        RadnikOperacijaQueue radnikOperacijaQueue = radnikOperacijaQueueRepo.findById(id).get();
        Zaposleni zaposleni = radnikOperacijaQueue.getZaposleni();

        radnikOperacijaQueueRepo.deleteById(id);
        if(zaposleni!=null){
            reorderRadnikOperacijaQueue(zaposleni.getId());
        }
        return "Uspesno obrisana";
    }

    private void reorderRadnikOperacijaQueue(Integer id) {
        List<RadnikOperacijaQueue> planirane =radnikOperacijaQueueRepo.findAllByZaposleniIdAndStatusOperacijeOrderByRedosledAsc(id, PLANIRANA);
        for (int i = 0; i < planirane.size(); i++) {
            planirane.get(i).setRedosled(i+1);
            System.out.println(planirane.get(i).getRedosled());
        }
        radnikOperacijaQueueRepo.saveAll(planirane);
    }

    public RadnikOperacijaQueue pauzirajAktuelnu(Integer zaposleniId) {
        RadnikOperacijaQueue aktuelna = getAktuelnaOperacija(zaposleniId);
        aktuelna.setRedosled(0);
        reorderRadnikOperacijaQueue(zaposleniId);
        RadnikOperacija radnikOperacija = new RadnikOperacija();
        radnikOperacija.setDatum(new Date());
        radnikOperacija.setPocetak(new Time(aktuelna.getPocetak().getTime()));
        radnikOperacija.setOperacija(aktuelna.getOperacija());
        radnikOperacija.setRadniNalog(aktuelna.getRadniNalog().getRadniNalog());
        radnikOperacija.setZaposleni(aktuelna.getZaposleni());
        radnikOperacija.setKraj(new Time(new Date().getTime()));
        radnikOperacija.setOpisPosla(aktuelna.getOperacija().getNaziv());
        radnikOperacijaRepo.save(radnikOperacija);
        aktuelna.setStatusOperacije(PLANIRANA);
        return radnikOperacijaQueueRepo.save(aktuelna);
    }

    public RadnikOperacijaQueue zameniAktuelnuOperaciju(Integer zaposleniId, Integer operacijaId, String radniNalogSifra) {
        RadnikOperacijaQueue nova = postPlaniranaOperacijaZaposleni(zaposleniId,operacijaId,radniNalogSifra);
        pauzirajAktuelnu(zaposleniId);
        putRadnikoperacijaQueueAktuelna(nova.getId(), zaposleniId);
        reorderRadnikOperacijaQueue(zaposleniId);
        return nova;
    }

    public List<RadnikOperacijaQueue> saveAll(List<RadnikOperacijaQueue> radnikOperacijaQueues) {
        return radnikOperacijaQueueRepo.saveAll(radnikOperacijaQueues);
    }
}
