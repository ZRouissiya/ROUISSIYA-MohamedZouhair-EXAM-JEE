package com.rouissiya.zouhair.dtos;

import com.rouissiya.zouhair.enums.NiveauCouvertureSante;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ContratSanteDTO extends ContratAssuranceDTO {
    private NiveauCouvertureSante niveauCouverture;
    private Integer nombrePersonnesCouvertes;
}