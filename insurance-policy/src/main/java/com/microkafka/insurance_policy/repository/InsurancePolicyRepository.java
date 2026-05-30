package com.microkafka.insurance_policy.repository;

import com.microkafka.insurance_policy.model.InsurancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Long> {
    List<InsurancePolicy> findByClientId(Long clientId);
}
