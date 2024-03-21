package com.konstil.Belezenje.radnih.vremena.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "operacije")
@ToString
public class Operacija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name = "ID")
    Integer id;
    @Column(name = "opis")
    String naziv;
}
