package com.microkafka.insurance_policy.service;

import com.microkafka.insurance_policy.dto.InsurancePolicyDto;
import com.microkafka.insurance_policy.model.InsurancePolicy;

import java.util.List;

public interface InsurancePolicyService {
    InsurancePolicy create(InsurancePolicyDto insurancePolicyDto);

    List<InsurancePolicy> findAll();

    List<InsurancePolicy> findByClientId(Long clientId);
}
