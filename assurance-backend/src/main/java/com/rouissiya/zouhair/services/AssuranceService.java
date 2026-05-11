package com.rouissiya.zouhair.services;

import com.rouissiya.zouhair.dtos.*;
import com.rouissiya.zouhair.dtos.*;

import java.math.BigDecimal;
import java.util.List;

public interface AssuranceService {
    ClientDTO ajouterClient(ClientDTO dto);
    ClientDTO modifierClient(Long id, ClientDTO dto);
    void supprimerClient(Long id);
    List<ClientDTO> rechercherClientParNom(String nom);
    List<ClientDTO> afficherTousLesClients();
    ClientDTO afficherClientParId(Long id);

    ContratAutomobileDTO creerContratAutomobile(NouveauContratAutomobileDTO dto);
    ContratHabitationDTO creerContratHabitation(NouveauContratHabitationDTO dto);
    ContratSanteDTO creerContratSante(NouveauContratSanteDTO dto);

    List<ContratAssuranceDTO> afficherTousLesContrats();
    ContratAssuranceDTO afficherContratParId(Long id);
    List<ContratAssuranceDTO> afficherContratsClient(Long clientId);
    ContratAssuranceDTO validerContrat(Long contratId);
    ContratAssuranceDTO resilierContrat(Long contratId);

    PaiementDTO ajouterPaiement(Long contratId, NouveauPaiementDTO dto);
    List<PaiementDTO> afficherPaiementsContrat(Long contratId);
    BigDecimal calculerTotalPaye(Long contratId);
    BigDecimal calculerResteAPayer(Long contratId);
    DashboardStatsDTO dashboardStats();
}
