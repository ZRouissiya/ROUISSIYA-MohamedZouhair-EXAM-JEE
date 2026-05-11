package com.rouissiya.zouhair.web;

import com.rouissiya.zouhair.dtos.ChangePasswordDTO;
import com.rouissiya.zouhair.dtos.LoginRequestDTO;
import com.rouissiya.zouhair.dtos.LoginResponseDTO;
import com.rouissiya.zouhair.dtos.RegisterRequestDTO;
import com.rouissiya.zouhair.entities.Role;
import com.rouissiya.zouhair.entities.Utilisateur;
import com.rouissiya.zouhair.enums.RoleName;
import com.rouissiya.zouhair.repositories.RoleRepository;
import com.rouissiya.zouhair.repositories.UtilisateurRepository;
import com.rouissiya.zouhair.security.JwtUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public LoginResponseDTO login(@Valid @RequestBody LoginRequestDTO request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User userDetails = (User) authentication.getPrincipal();
        String token = jwtUtils.generateToken(userDetails);
        List<String> roles = userDetails.getAuthorities().stream().map(a -> a.getAuthority()).toList();

        return new LoginResponseDTO(token, "Bearer", userDetails.getUsername(), roles);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequestDTO request) {
        if (utilisateurRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body("Username déjà utilisé");
        }
        if (utilisateurRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Email déjà utilisé");
        }

        RoleName roleName = parseRole(request.getRole());
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new IllegalStateException("Role introuvable: " + roleName));

        Utilisateur user = Utilisateur.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of(role))
                .build();

        utilisateurRepository.save(user);
        return ResponseEntity.ok("Utilisateur enregistré avec succès");
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@Valid @RequestBody ChangePasswordDTO dto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Utilisateur user = utilisateurRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("Utilisateur introuvable"));

        if (!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body("Ancien mot de passe incorrect");
        }

        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        utilisateurRepository.save(user);
        return ResponseEntity.ok("Mot de passe modifié");
    }

    private RoleName parseRole(String role) {
        if (role == null || role.isBlank()) {
            return RoleName.ROLE_CLIENT;
        }
        return switch (role.toUpperCase()) {
            case "ROLE_ADMIN", "ADMIN" -> RoleName.ROLE_ADMIN;
            case "ROLE_EMPLOYE", "EMPLOYE" -> RoleName.ROLE_EMPLOYE;
            default -> RoleName.ROLE_CLIENT;
        };
    }
}
