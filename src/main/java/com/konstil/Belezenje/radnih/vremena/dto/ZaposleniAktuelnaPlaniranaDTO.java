package com.konstil.Belezenje.radnih.vremena.dto;

import com.konstil.Belezenje.radnih.vremena.domain.RadnikOperacijaQueue;
import com.konstil.Belezenje.radnih.vremena.domain.Zaposleni;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ZaposleniAktuelnaPlaniranaDTO {
    Zaposleni zaposleni;
    RadnikOperacijaQueue aktuelna;
    RadnikOperacijaQueue planirana;
}
