package com.microkafka.claims.dto;

import java.time.LocalDate;

public record ClaimDto(Long policyId, String description, LocalDate creationDate) {
}
