package com.example.assurance.services;

import com.example.assurance.dtos.*;
import com.example.assurance.entities.*;
import com.example.assurance.enums.StatutContrat;
import com.example.assurance.exceptions.BusinessException;
import com.example.assurance.exceptions.ResourceNotFoundException;
import com.example.assurance.mappers.AssuranceMapper;
import com.example.assurance.repositories.ClientRepository;
import com.example.assurance.repositories.ContratAssuranceRepository;
import com.example.assurance.repositories.PaiementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AssuranceServiceImpl implements AssuranceService {

    private final ClientRepository clientRepository;
    private final ContratAssuranceRepository contratRepository;
    private final PaiementRepository paiementRepository;

    @Override
    public ClientDTO ajouterClient(ClientDTO dto) {
        Client client = AssuranceMapper.toClientEntity(dto);
        client.setId(null);
        return AssuranceMapper.toClientDTO(clientRepository.save(client));
    }

    @Override
    public ClientDTO modifierClient(Long id, ClientDTO dto) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client introuvable: " + id));
        client.setNom(dto.getNom());
        client.setEmail(dto.getEmail());
        return AssuranceMapper.toClientDTO(clientRepository.save(client));
    }

    @Override
    public void supprimerClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Client introuvable: " + id);
        }
        clientRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientDTO> rechercherClientParNom(String nom) {
        return clientRepository.findByNomContainingIgnoreCase(nom)
                .stream()
                .map(AssuranceMapper::toClientDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientDTO> afficherTousLesClients() {
        return clientRepository.findAll().stream().map(AssuranceMapper::toClientDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ClientDTO afficherClientParId(Long id) {
        return AssuranceMapper.toClientDTO(
                clientRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Client introuvable: " + id))
        );
    }

    @Override
    public ContratAutomobileDTO creerContratAutomobile(NouveauContratAutomobileDTO dto) {
        Client client = findClient(dto.getClientId());
        ContratAssuranceAutomobile contrat = new ContratAssuranceAutomobile();
        applyBaseContrat(contrat, dto.getDateSouscription(), dto.getMontantCotisation(), dto.getDureeContrat(), dto.getTauxCouverture(), client);
        contrat.setNumeroImmatriculation(dto.getNumeroImmatriculation());
        contrat.setMarqueVehicule(dto.getMarqueVehicule());
        contrat.setModeleVehicule(dto.getModeleVehicule());
        return AssuranceMapper.toContratAutoDTO((ContratAssuranceAutomobile) contratRepository.save(contrat));
    }

    @Override
    public ContratHabitationDTO creerContratHabitation(NouveauContratHabitationDTO dto) {
        Client client = findClient(dto.getClientId());
        ContratAssuranceHabitation contrat = new ContratAssuranceHabitation();
        applyBaseContrat(contrat, dto.getDateSouscription(), dto.getMontantCotisation(), dto.getDureeContrat(), dto.getTauxCouverture(), client);
        contrat.setTypeLogement(dto.getTypeLogement());
        contrat.setAdresseLogement(dto.getAdresseLogement());
        contrat.setSuperficie(dto.getSuperficie());
        return AssuranceMapper.toContratHabitationDTO((ContratAssuranceHabitation) contratRepository.save(contrat));
    }

    @Override
    public ContratSanteDTO creerContratSante(NouveauContratSanteDTO dto) {
        Client client = findClient(dto.getClientId());
        ContratAssuranceSante contrat = new ContratAssuranceSante();
        applyBaseContrat(contrat, dto.getDateSouscription(), dto.getMontantCotisation(), dto.getDureeContrat(), dto.getTauxCouverture(), client);
        contrat.setNiveauCouverture(dto.getNiveauCouverture());
        contrat.setNombrePersonnesCouvertes(dto.getNombrePersonnesCouvertes());
        return AssuranceMapper.toContratSanteDTO((ContratAssuranceSante) contratRepository.save(contrat));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContratAssuranceDTO> afficherTousLesContrats() {
        return contratRepository.findAll().stream().map(AssuranceMapper::toContratDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ContratAssuranceDTO afficherContratParId(Long id) {
        return AssuranceMapper.toContratDTO(findContrat(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContratAssuranceDTO> afficherContratsClient(Long clientId) {
        return contratRepository.findByClientId(clientId).stream().map(AssuranceMapper::toContratDTO).toList();
    }

    @Override
    public ContratAssuranceDTO validerContrat(Long contratId) {
        ContratAssurance contrat = findContrat(contratId);
        if (contrat.getStatut() == StatutContrat.RESILIE) {
            throw new BusinessException("Impossible de valider un contrat résilié");
        }
        contrat.setStatut(StatutContrat.VALIDE);
        contrat.setDateValidation(LocalDate.now());
        return AssuranceMapper.toContratDTO(contratRepository.save(contrat));
    }

    @Override
    public ContratAssuranceDTO resilierContrat(Long contratId) {
        ContratAssurance contrat = findContrat(contratId);
        contrat.setStatut(StatutContrat.RESILIE);
        return AssuranceMapper.toContratDTO(contratRepository.save(contrat));
    }

    @Override
    public PaiementDTO ajouterPaiement(Long contratId, NouveauPaiementDTO dto) {
        ContratAssurance contrat = findContrat(contratId);
        if (contrat.getStatut() == StatutContrat.RESILIE) {
            throw new BusinessException("Impossible d'ajouter un paiement sur un contrat résilié");
        }
        Paiement paiement = Paiement.builder()
                .date(dto.getDate())
                .montant(dto.getMontant())
                .type(dto.getType())
                .contrat(contrat)
                .build();
        return AssuranceMapper.toPaiementDTO(paiementRepository.save(paiement));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaiementDTO> afficherPaiementsContrat(Long contratId) {
        findContrat(contratId);
        return paiementRepository.findByContratId(contratId)
                .stream()
                .map(AssuranceMapper::toPaiementDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal calculerTotalPaye(Long contratId) {
        findContrat(contratId);
        return paiementRepository.findByContratId(contratId)
                .stream()
                .map(Paiement::getMontant)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal calculerResteAPayer(Long contratId) {
        ContratAssurance contrat = findContrat(contratId);
        BigDecimal totalPaye = calculerTotalPaye(contratId);
        BigDecimal montantTotalContrat = contrat.getMontantCotisation().multiply(BigDecimal.valueOf(contrat.getDureeContrat()));
        BigDecimal reste = montantTotalContrat.subtract(totalPaye);
        return reste.max(BigDecimal.ZERO);
    }

    @Override
    @Transactional(readOnly = true)
    public DashboardStatsDTO dashboardStats() {
        DashboardStatsDTO dto = new DashboardStatsDTO();
        dto.setTotalClients(clientRepository.count());
        dto.setTotalContrats(contratRepository.count());
        dto.setTotalPaiements(paiementRepository.count());

        List<ContratAssurance> contrats = contratRepository.findAll();
        dto.setContratsParType(contrats.stream()
                .collect(Collectors.groupingBy(c -> c.getClass().getSimpleName(), Collectors.counting())));
        dto.setContratsParStatut(contrats.stream()
                .collect(Collectors.groupingBy(c -> c.getStatut().name(), Collectors.counting())));

        List<Paiement> paiements = paiementRepository.findAll();
        dto.setPaiementsParType(paiements.stream()
                .collect(Collectors.groupingBy(p -> p.getType().name(), Collectors.counting())));

        Map<String, Double> mensuel = paiements.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getDate().getYear() + "-" + String.format("%02d", p.getDate().getMonthValue()),
                        TreeMap::new,
                        Collectors.reducing(0.0, p -> p.getMontant().doubleValue(), Double::sum)
                ));
        dto.setStatistiquesMensuellesPaiements(mensuel);
        return dto;
    }

    private Client findClient(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client introuvable: " + id));
    }

    private ContratAssurance findContrat(Long id) {
        return contratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat introuvable: " + id));
    }

    private void applyBaseContrat(ContratAssurance contrat,
                                  LocalDate dateSouscription,
                                  BigDecimal montantCotisation,
                                  Integer dureeContrat,
                                  Double tauxCouverture,
                                  Client client) {
        contrat.setDateSouscription(dateSouscription);
        contrat.setMontantCotisation(montantCotisation);
        contrat.setDureeContrat(dureeContrat);
        contrat.setTauxCouverture(tauxCouverture);
        contrat.setStatut(StatutContrat.EN_COURS);
        contrat.setClient(client);
    }
}