package com.rouissiya.zouhair.dtos;

import com.rouissiya.zouhair.enums.TypePaiement;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class NouveauPaiementDTO {
    @NotNull
    private LocalDate date;
    @NotNull
    @DecimalMin("0.0")
    private BigDecimal montant;
    @NotNull
    private TypePaiement type;
}