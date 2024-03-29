package com.konstil.Belezenje.radnih.vremena.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "radninalog")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RadniNalog {
    @Id
    private Integer id;
    @Column(name = "kasa")
    private String sifra;
    private Date pocetak;
    private Date zatvoren;
    @Enumerated(jakarta.persistence.EnumType.STRING)
    private StanjeNaloga stanje;

    @Column(name = "rok")
    private Date rok;

    @ManyToOne
            Proizvod proizvod;


    String naziv;


}
