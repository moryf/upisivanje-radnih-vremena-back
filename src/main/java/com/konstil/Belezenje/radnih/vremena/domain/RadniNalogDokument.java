package com.konstil.Belezenje.radnih.vremena.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "radninalog_dokumenti")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RadniNalogDokument {
    @Id
    private Integer id;
    @ManyToOne
    @JoinColumn(name="rn")
    private RadniNalog radniNalog;
    private String naziv;
    private String link;

}
