package com.konstil.Belezenje.radnih.vremena.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RadnikOperacijaQueue {
    @Enumerated(EnumType.STRING)
    StatusOperacije statusOperacije;
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    Zaposleni zaposleni;
    @ManyToOne
    @JoinColumn(referencedColumnName = "ID", nullable = false)

    Operacija operacija;
    @ManyToOne
    RadniNalog radniNalog;
    Date pocetak;
    @ManyToOne
    Masina masina;

    int redosled;
}
