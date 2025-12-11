package com.elections.elections.model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ElectionDTO {
    @NotBlank(message = "Election name cannot be blank")
    private String name;

    @NotNull(message = "Election Start Date cannot be blank")
    private LocalDateTime startDateTime;

    @NotNull(message = "Election End Date cannot be blank")
    private LocalDateTime endDateTime;
}
