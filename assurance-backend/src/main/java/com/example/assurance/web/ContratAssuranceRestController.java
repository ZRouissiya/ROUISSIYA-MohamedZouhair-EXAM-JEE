package com.example.assurance.web;

import com.example.assurance.dtos.*;
import com.example.assurance.services.AssuranceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contrats")
@RequiredArgsConstructor
public class ContratAssuranceRestController {

    private final AssuranceService assuranceService;

    @PostMapping("/automobile")
    public ContratAutomobileDTO creerContratAutomobile(@Valid @RequestBody NouveauContratAutomobileDTO dto) {
        return assuranceService.creerContratAutomobile(dto);
    }

    @PostMapping("/habitation")
    public ContratHabitationDTO creerContratHabitation(@Valid @RequestBody NouveauContratHabitationDTO dto) {
        return assuranceService.creerContratHabitation(dto);
    }

    @PostMapping("/sante")
    public ContratSanteDTO creerContratSante(@Valid @RequestBody NouveauContratSanteDTO dto) {
        return assuranceService.creerContratSante(dto);
    }

    @GetMapping
    public List<ContratAssuranceDTO> getAllContrats() {
        return assuranceService.afficherTousLesContrats();
    }

    @GetMapping("/{id}")
    public ContratAssuranceDTO getContratById(@PathVariable Long id) {
        return assuranceService.afficherContratParId(id);
    }

    @GetMapping("/client/{clientId}")
    public List<ContratAssuranceDTO> getContratsClient(@PathVariable Long clientId) {
        return assuranceService.afficherContratsClient(clientId);
    }

    @PutMapping("/{id}/valider")
    public ContratAssuranceDTO validerContrat(@PathVariable Long id) {
        return assuranceService.validerContrat(id);
    }

    @PutMapping("/{id}/resilier")
    public ContratAssuranceDTO resilierContrat(@PathVariable Long id) {
        return assuranceService.resilierContrat(id);
    }
}