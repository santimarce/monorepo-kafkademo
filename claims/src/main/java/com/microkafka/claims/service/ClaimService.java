package com.microkafka.claims.service;

import com.microkafka.claims.dto.ClaimDto;
import com.microkafka.claims.model.Claim;

import java.util.List;

public interface ClaimService {
    Claim create(ClaimDto claimDto);

    List<Claim> findAll();

    List<Claim> findByPolicyId(Long policyId);
}
