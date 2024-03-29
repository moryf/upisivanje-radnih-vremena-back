package com.konstil.Belezenje.radnih.vremena.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProizvodOperacija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    Operacija operacija;
    @ManyToOne
    Proizvod proizvod;
    @ManyToOne
    Masina masina;
    @ManyToOne
    Alat alat;
    private Integer redosled;
}
