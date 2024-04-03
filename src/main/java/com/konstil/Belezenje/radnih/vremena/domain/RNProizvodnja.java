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
public class RNProizvodnja {

    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @OneToOne
    RadniNalog radniNalog;

    Date otvoren;
    Status status;

    Date zatvoren;

    @ManyToOne
    Proizvod proizvod;

    @ManyToOne
            Zaposleni nadlezni;

    Tip tip;

    public enum Tip {
        STANDARD,
        KONSTRUKCIJA
    }

    public enum Status {
        ZAVRSEN,
        U_TOKU
    }
}
