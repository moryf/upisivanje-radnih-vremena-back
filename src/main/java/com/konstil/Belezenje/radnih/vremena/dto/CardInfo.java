package com.konstil.Belezenje.radnih.vremena.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CardInfo {
        private String DATA;
        private String SN;
        private int CTRLINFO;
        private int online;
        private String OSN;
        private String UID;
}
