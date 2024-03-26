package com.konstil.Belezenje.radnih.vremena.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "masine")
public class Masina {
    @Id
    private Integer id;
    private String naziv;
    private String opis;

}
