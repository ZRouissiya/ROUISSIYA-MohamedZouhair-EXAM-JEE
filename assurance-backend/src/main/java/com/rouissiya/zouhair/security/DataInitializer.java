package com.rouissiya.zouhair.security;

import com.rouissiya.zouhair.entities.Role;
import com.rouissiya.zouhair.entities.Utilisateur;
import com.rouissiya.zouhair.enums.RoleName;
import com.rouissiya.zouhair.repositories.RoleRepository;
import com.rouissiya.zouhair.repositories.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        Role roleClient = roleRepository.findByName(RoleName.ROLE_CLIENT)
                .orElseGet(() -> roleRepository.save(Role.builder().name(RoleName.ROLE_CLIENT).build()));
        Role roleEmploye = roleRepository.findByName(RoleName.ROLE_EMPLOYE)
                .orElseGet(() -> roleRepository.save(Role.builder().name(RoleName.ROLE_EMPLOYE).build()));
        Role roleAdmin = roleRepository.findByName(RoleName.ROLE_ADMIN)
                .orElseGet(() -> roleRepository.save(Role.builder().name(RoleName.ROLE_ADMIN).build()));

        if (!utilisateurRepository.existsByUsername("admin")) {
            Utilisateur admin = Utilisateur.builder()
                    .username("admin")
                    .email("admin@assurance.local")
                    .password(passwordEncoder.encode("Admin123!"))
                    .roles(Set.of(roleAdmin, roleEmploye, roleClient))
                    .build();
            utilisateurRepository.save(admin);
        }
    }
}