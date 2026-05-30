package com.microkafka.insurance_policy.service.impl;

import com.microkafka.insurance_policy.dto.InsurancePolicyDto;
import com.microkafka.insurance_policy.model.InsurancePolicy;
import com.microkafka.insurance_policy.repository.InsurancePolicyRepository;
import com.microkafka.insurance_policy.service.InsurancePolicyService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InsurancePolicyServiceImpl implements InsurancePolicyService {
    private final InsurancePolicyRepository insurancePolicyRepository;

    public InsurancePolicyServiceImpl(InsurancePolicyRepository insurancePolicyRepository) {
        this.insurancePolicyRepository = insurancePolicyRepository;
    }

    @Override
    public InsurancePolicy create(InsurancePolicyDto insurancePolicyDto) {
        InsurancePolicy policy = InsurancePolicy.builder()
                .policyNumber(insurancePolicyDto.policyNumber())
                .policyType(insurancePolicyDto.policyType())
                .creationDate(LocalDate.now())
                .validityDate(insurancePolicyDto.validityDate())
                .clientId(insurancePolicyDto.clientId())
                .build();

        return insurancePolicyRepository.save(policy);
    }

    @Override
    public List<InsurancePolicy> findAll() {
        return insurancePolicyRepository.findAll();
    }

    @Override
    public List<InsurancePolicy> findByClientId(Long clientId) {
        return insurancePolicyRepository.findByClientId(clientId);
    }
}
