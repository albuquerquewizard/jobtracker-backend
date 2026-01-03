package com.example.jobtracker.dto;

import lombok.Data;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class JobtrackerAppDTO {
    private Long id;

    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotBlank(message = "Role is required")
    private String role;

    @NotBlank(message = "Status is required")
    private String status;

    @NotNull(message = "Applied date is required")
    private LocalDate appliedDate;
}
