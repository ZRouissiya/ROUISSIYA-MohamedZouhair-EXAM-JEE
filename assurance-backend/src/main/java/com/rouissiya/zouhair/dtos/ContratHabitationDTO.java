package com.rouissiya.zouhair.dtos;

import com.rouissiya.zouhair.enums.TypeLogement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ContratHabitationDTO extends ContratAssuranceDTO {
    private TypeLogement typeLogement;
    private String adresseLogement;
    private Double superficie;
}