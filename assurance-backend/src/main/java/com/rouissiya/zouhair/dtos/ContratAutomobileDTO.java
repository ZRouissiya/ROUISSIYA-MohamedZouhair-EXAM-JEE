package com.rouissiya.zouhair.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ContratAutomobileDTO extends ContratAssuranceDTO {
    private String numeroImmatriculation;
    private String marqueVehicule;
    private String modeleVehicule;
}