package com.konstil.Belezenje.radnih.vremena.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "radnici")
@ToString
public class Zaposleni {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    Integer id;

    @Column(name = "name")
    String ime;

    String korisnickoIme;

    String lozinka;
    @Enumerated(jakarta.persistence.EnumType.STRING)
    Uloga uloga;
    @Enumerated(jakarta.persistence.EnumType.STRING)
    Active active;
    @ManyToMany
    List<Zaposleni> podredjeni;

    @ManyToMany
            @JoinTable(name = "zaposleni_operacija_kvalifikacije",
                    joinColumns = @JoinColumn(name = "zaposleni_id"),
                    inverseJoinColumns = @JoinColumn(name = "operacija_id"))
    List<Operacija> kvalifikacije;

    @Enumerated(jakarta.persistence.EnumType.STRING)
    Rezim rezim;

    @Column(unique = true, name = "card_uid")
    String cardUID;

    public enum Rezim{
    STANDARD,KONSTRUKCIJE
    }

    public enum Active{
        DA,NE
    }

}
