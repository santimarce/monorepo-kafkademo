package com.microkafka.insurance_policy.dto;

import java.time.LocalDate;

public record InsurancePolicyDto(String policyNumber, String policyType, LocalDate validityDate, Long clientId) {}
