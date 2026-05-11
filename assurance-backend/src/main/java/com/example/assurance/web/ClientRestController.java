package com.example.assurance.web;

import com.example.assurance.dtos.ClientDTO;
import com.example.assurance.services.AssuranceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientRestController {

    private final AssuranceService assuranceService;

    @PostMapping
    public ClientDTO ajouterClient(@Valid @RequestBody ClientDTO dto) {
        return assuranceService.ajouterClient(dto);
    }

    @PutMapping("/{id}")
    public ClientDTO modifierClient(@PathVariable Long id, @Valid @RequestBody ClientDTO dto) {
        return assuranceService.modifierClient(id, dto);
    }

    @DeleteMapping("/{id}")
    public void supprimerClient(@PathVariable Long id) {
        assuranceService.supprimerClient(id);
    }

    @GetMapping("/search")
    public List<ClientDTO> rechercherParNom(@RequestParam String nom) {
        return assuranceService.rechercherClientParNom(nom);
    }

    @GetMapping
    public List<ClientDTO> getAllClients() {
        return assuranceService.afficherTousLesClients();
    }

    @GetMapping("/{id}")
    public ClientDTO getClientById(@PathVariable Long id) {
        return assuranceService.afficherClientParId(id);
    }
}