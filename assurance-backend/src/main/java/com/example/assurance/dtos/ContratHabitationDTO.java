package com.example.assurance.dtos;

import com.example.assurance.enums.TypeLogement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ContratHabitationDTO extends ContratAssuranceDTO {
    private TypeLogement typeLogement;
    private String adresseLogement;
    private Double superficie;
}