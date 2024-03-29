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
public class SablonOperacija {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @ManyToOne
    TipProizvoda tipProizvoda;
    @ManyToOne
    Operacija operacija;
    int redosled;
}
