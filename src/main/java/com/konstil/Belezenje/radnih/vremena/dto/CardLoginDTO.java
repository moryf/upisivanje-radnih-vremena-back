package com.konstil.Belezenje.radnih.vremena.dto;

import com.konstil.Belezenje.radnih.vremena.domain.Zaposleni;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardLoginDTO {
    Zaposleni zaposleni;
    int brojCitaca;

}
