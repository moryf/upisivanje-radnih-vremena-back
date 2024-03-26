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
public class SablonOperacija {
    @Id
    Integer id;
    @ManyToOne
    SablonTipProizvoda sablon;
    @ManyToOne
    Operacija operacija;
    int redosled;
}
