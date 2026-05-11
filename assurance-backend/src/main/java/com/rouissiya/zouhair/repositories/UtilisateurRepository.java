package com.rouissiya.zouhair.repositories;

import com.rouissiya.zouhair.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}