package com.konstil.Belezenje.radnih.vremena.repository;

import com.konstil.Belezenje.radnih.vremena.domain.RadnikOperacijaQueue;
import com.konstil.Belezenje.radnih.vremena.domain.StatusOperacije;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RadnikOperacijaQueueRepo extends JpaRepository<RadnikOperacijaQueue, Long> {
    RadnikOperacijaQueue findByZaposleniIdAndAndStatusOperacije(Integer zaposleniId, StatusOperacije statusOperacije);

    RadnikOperacijaQueue findFirstByZaposleniIdAndStatusOperacije(Integer zaposleniId, StatusOperacije statusOperacije);

    List<RadnikOperacijaQueue> findAllByZaposleniIdAndStatusOperacijeOrderByRadniNalogRokAsc(Integer zaposleniId, StatusOperacije statusOperacije);


    @Query("select max(roq.redosled) from RadnikOperacijaQueue roq where roq.zaposleni.id = ?1")
    int findMaxRedosledByZaposleniId(Integer zaposleniId);
}
