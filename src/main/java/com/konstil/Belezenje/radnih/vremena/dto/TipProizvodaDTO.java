package com.konstil.Belezenje.radnih.vremena.dto;

import com.konstil.Belezenje.radnih.vremena.domain.Proizvod;
import com.konstil.Belezenje.radnih.vremena.domain.TipProizvoda;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TipProizvodaDTO {
    TipProizvoda tipProizvoda;
    List<Proizvod> proizvodi;
}
