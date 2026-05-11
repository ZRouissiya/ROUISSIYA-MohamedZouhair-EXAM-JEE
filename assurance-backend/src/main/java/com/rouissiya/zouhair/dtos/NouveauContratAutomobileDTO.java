package com.rouissiya.zouhair.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class NouveauContratAutomobileDTO {
    @NotNull
    private LocalDate dateSouscription;
    @NotNull
    @DecimalMin("0.0")
    private BigDecimal montantCotisation;
    @NotNull
    @Positive
    private Integer dureeContrat;
    @NotNull
    @DecimalMin("0.0")
    @DecimalMax("100.0")
    private Double tauxCouverture;
    @NotNull
    private Long clientId;
    @NotBlank
    private String numeroImmatriculation;
    @NotBlank
    private String marqueVehicule;
    @NotBlank
    private String modeleVehicule;
}