package com.konstil.Belezenje.radnih.vremena.repository;

import com.konstil.Belezenje.radnih.vremena.domain.RadnikOperacijaQueue;
import com.konstil.Belezenje.radnih.vremena.domain.StatusOperacije;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RadnikOperacijaQueueRepo extends JpaRepository<RadnikOperacijaQueue, Long> {
    RadnikOperacijaQueue findByZaposleniIdAndAndStatusOperacije(Integer zaposleniId, StatusOperacije statusOperacije);

    RadnikOperacijaQueue findFirstByZaposleniIdAndStatusOperacije(Integer zaposleniId, StatusOperacije statusOperacije);

    List<RadnikOperacijaQueue> findAllByZaposleniIdAndStatusOperacije(Integer zaposleniId, StatusOperacije statusOperacije);


}
