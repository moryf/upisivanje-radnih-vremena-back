package com.konstil.Belezenje.radnih.vremena.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "radnik_evidencija")
@ToString
public class RadnikOperacija {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
            @Column(name = "ID")
    Integer id;
    @ManyToOne
            @JoinColumn(name="radnik")
    Zaposleni zaposleni;
    @ManyToOne
            @JoinColumn(name="operacija")
    Operacija operacija;
    @ManyToOne
            @JoinColumn(name="nalog")
    RadniNalog radniNalog;

    Date datum = new Date();

    @Column(name = "vreme_pocetka")
    Time pocetak = new Time(new Date().getTime());
    @Column(name = "vreme_kraja")
    Time kraj = new Time(new Date().getTime());

    @Column(name = "opis_posla")
    String opisPosla;
}
