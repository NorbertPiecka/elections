package com.elections.elections.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VoteDTO {
    @NotNull(message = "ElectionId is mandatory")
    private Long electionId;

    @NotNull(message = "CandidateId is mandatory")
    private Long candidateId;
}
