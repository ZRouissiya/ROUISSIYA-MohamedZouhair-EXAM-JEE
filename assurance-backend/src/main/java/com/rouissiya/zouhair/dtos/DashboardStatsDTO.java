package com.rouissiya.zouhair.dtos;

import lombok.Data;

import java.util.Map;

@Data
public class DashboardStatsDTO {
    private long totalClients;
    private long totalContrats;
    private long totalPaiements;
    private Map<String, Long> contratsParType;
    private Map<String, Long> contratsParStatut;
    private Map<String, Long> paiementsParType;
    private Map<String, Double> statistiquesMensuellesPaiements;
}