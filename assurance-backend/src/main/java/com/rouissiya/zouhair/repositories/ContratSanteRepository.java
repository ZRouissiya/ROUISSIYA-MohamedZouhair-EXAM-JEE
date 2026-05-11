package com.rouissiya.zouhair.repositories;

import com.rouissiya.zouhair.entities.ContratAssuranceSante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratSanteRepository extends JpaRepository<ContratAssuranceSante, Long> {
}