package com.rouissiya.zouhair.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChangePasswordDTO {
    @NotBlank
    private String oldPassword;
    @NotBlank
    private String newPassword;
}