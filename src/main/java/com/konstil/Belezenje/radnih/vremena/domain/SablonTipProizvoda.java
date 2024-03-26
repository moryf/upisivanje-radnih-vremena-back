package com.konstil.Belezenje.radnih.vremena.domain;

import jakarta.persistence.*;

@Entity
public class SablonTipProizvoda {
    @Id
    Integer id;
    @OneToOne
            @JoinColumn(unique = true)
    TipProizvoda tipProizvoda;




}
