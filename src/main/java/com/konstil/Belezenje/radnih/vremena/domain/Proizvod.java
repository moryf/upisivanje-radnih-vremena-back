package com.konstil.Belezenje.radnih.vremena.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Proizvod {
    @Id
    Integer id;
    String naziv;
    String opis;

    @ManyToOne
    TipProizvoda tipProizvoda;

}
