package com.rouissiya.zouhair.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClientDTO {
    private Long id;

    @NotBlank
    private String nom;

    @NotBlank
    @Email
    private String email;
}