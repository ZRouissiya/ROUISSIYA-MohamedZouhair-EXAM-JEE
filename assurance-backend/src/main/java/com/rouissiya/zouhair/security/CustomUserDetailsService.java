package com.rouissiya.zouhair.security;

import com.rouissiya.zouhair.entities.Utilisateur;
import com.rouissiya.zouhair.repositories.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur user = utilisateurRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles().stream()
                        .map(r -> new SimpleGrantedAuthority(r.getName().name()))
                        .collect(Collectors.toSet()))
                .build();
    }
}