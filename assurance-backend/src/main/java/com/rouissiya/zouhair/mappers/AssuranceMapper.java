package com.rouissiya.zouhair.mappers;

import com.rouissiya.zouhair.dtos.*;
import com.rouissiya.zouhair.entities.*;

public final class AssuranceMapper {
    private AssuranceMapper() {
    }

    public static ClientDTO toClientDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setNom(client.getNom());
        dto.setEmail(client.getEmail());
        return dto;
    }

    public static Client toClientEntity(ClientDTO dto) {
        Client client = new Client();
        client.setId(dto.getId());
        client.setNom(dto.getNom());
        client.setEmail(dto.getEmail());
        return client;
    }

    public static ContratAssuranceDTO toContratDTO(ContratAssurance contrat) {
        if (contrat instanceof ContratAssuranceAutomobile auto) {
            return toContratAutoDTO(auto);
        }
        if (contrat instanceof ContratAssuranceHabitation hab) {
            return toContratHabitationDTO(hab);
        }
        if (contrat instanceof ContratAssuranceSante sante) {
            return toContratSanteDTO(sante);
        }
        ContratAssuranceDTO dto = new ContratAssuranceDTO();
        fillContratBase(dto, contrat);
        return dto;
    }

    public static ContratAutomobileDTO toContratAutoDTO(ContratAssuranceAutomobile contrat) {
        ContratAutomobileDTO dto = new ContratAutomobileDTO();
        fillContratBase(dto, contrat);
        dto.setType("AUTOMOBILE");
        dto.setNumeroImmatriculation(contrat.getNumeroImmatriculation());
        dto.setMarqueVehicule(contrat.getMarqueVehicule());
        dto.setModeleVehicule(contrat.getModeleVehicule());
        return dto;
    }

    public static ContratHabitationDTO toContratHabitationDTO(ContratAssuranceHabitation contrat) {
        ContratHabitationDTO dto = new ContratHabitationDTO();
        fillContratBase(dto, contrat);
        dto.setType("HABITATION");
        dto.setTypeLogement(contrat.getTypeLogement());
        dto.setAdresseLogement(contrat.getAdresseLogement());
        dto.setSuperficie(contrat.getSuperficie());
        return dto;
    }

    public static ContratSanteDTO toContratSanteDTO(ContratAssuranceSante contrat) {
        ContratSanteDTO dto = new ContratSanteDTO();
        fillContratBase(dto, contrat);
        dto.setType("SANTE");
        dto.setNiveauCouverture(contrat.getNiveauCouverture());
        dto.setNombrePersonnesCouvertes(contrat.getNombrePersonnesCouvertes());
        return dto;
    }

    private static void fillContratBase(ContratAssuranceDTO dto, ContratAssurance contrat) {
        dto.setId(contrat.getId());
        dto.setDateSouscription(contrat.getDateSouscription());
        dto.setStatut(contrat.getStatut());
        dto.setDateValidation(contrat.getDateValidation());
        dto.setMontantCotisation(contrat.getMontantCotisation());
        dto.setDureeContrat(contrat.getDureeContrat());
        dto.setTauxCouverture(contrat.getTauxCouverture());
        dto.setClientId(contrat.getClient() != null ? contrat.getClient().getId() : null);
    }

    public static PaiementDTO toPaiementDTO(Paiement paiement) {
        PaiementDTO dto = new PaiementDTO();
        dto.setId(paiement.getId());
        dto.setDate(paiement.getDate());
        dto.setMontant(paiement.getMontant());
        dto.setType(paiement.getType());
        dto.setContratId(paiement.getContrat() != null ? paiement.getContrat().getId() : null);
        return dto;
    }
}
