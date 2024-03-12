package com.konstil.Belezenje.radnih.vremena.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RadnikOperacijaQueue {
    @Enumerated(EnumType.STRING)
    StatusOperacije statusOperacije;
    @Id
    Long id;

    @ManyToOne
    Zaposleni zaposleni;
    @ManyToOne
    Operacija operacija;
    @ManyToOne
    RadniNalog radniNalog;
    Date pocetak;
}
