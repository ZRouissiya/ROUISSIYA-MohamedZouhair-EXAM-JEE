package com.rouissiya.zouhair.dtos;

import com.rouissiya.zouhair.enums.TypeLogement;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class NouveauContratHabitationDTO {
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
    @NotNull
    private TypeLogement typeLogement;
    @NotBlank
    private String adresseLogement;
    @NotNull
    @Positive
    private Double superficie;
}