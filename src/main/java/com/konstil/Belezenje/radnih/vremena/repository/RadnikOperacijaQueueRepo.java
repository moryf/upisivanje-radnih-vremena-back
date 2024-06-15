package com.konstil.Belezenje.radnih.vremena.repository;

import com.konstil.Belezenje.radnih.vremena.domain.RadnikOperacijaQueue;
import com.konstil.Belezenje.radnih.vremena.domain.StatusOperacije;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RadnikOperacijaQueueRepo extends JpaRepository<RadnikOperacijaQueue, Long> {
    RadnikOperacijaQueue findByZaposleniIdAndAndStatusOperacije(Integer zaposleniId, StatusOperacije statusOperacije);

    RadnikOperacijaQueue findFirstByZaposleniIdAndStatusOperacijeOrderByRedosledAsc(Integer zaposleniId, StatusOperacije statusOperacije);

    @Query("select roq from RadnikOperacijaQueue roq where roq.zaposleni.id = ?1 and roq.statusOperacije = ?2 order by roq.radniNalog.radniNalog.rok asc")
    List<RadnikOperacijaQueue> findAllByZaposleniIdAndStatusOperacijeOrderByRadniNalogRokAsc(Integer zaposleniId, StatusOperacije statusOperacije);


    @Query("select max(roq.redosled) from RadnikOperacijaQueue roq where roq.zaposleni.id = ?1")
    Optional<Integer> findMaxRedosledByZaposleniId(Integer zaposleniId);

    @Query("select roq from RadnikOperacijaQueue roq where roq.statusOperacije = ?1 order by roq.radniNalog.radniNalog.rok asc, roq.redosled asc")
    List<RadnikOperacijaQueue> findAllByStatusOperacijeOrderByRadniNalogRokAscRedosledAsc(StatusOperacije statusOperacije);

    List<RadnikOperacijaQueue> findAllByZaposleniIdAndStatusOperacijeOrderByRedosledAsc(Integer id, StatusOperacije statusOperacije);
}
