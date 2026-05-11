package com.rouissiya.zouhair.dtos;

import com.rouissiya.zouhair.enums.TypePaiement;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PaiementDTO {
    private Long id;
    private LocalDate date;
    private BigDecimal montant;
    private TypePaiement type;
    private Long contratId;
}