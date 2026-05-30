package com.microkafka.claims.service.impl;

import com.microkafka.claims.dto.ClaimDto;
import com.microkafka.claims.model.Claim;
import com.microkafka.claims.repository.ClaimRepository;
import com.microkafka.claims.service.ClaimService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ClaimServiceImpl implements ClaimService {
    private static final String CLAIM_TOPIC = "claim-topic";

    private final ClaimRepository claimRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public ClaimServiceImpl(ClaimRepository claimRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.claimRepository = claimRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public Claim create(ClaimDto claimDto) {
        Claim claim = Claim.builder()
                .policyId(claimDto.policyId())
                .description(claimDto.description())
                .creationDate(claimDto.creationDate() != null ? claimDto.creationDate() : LocalDate.now())
                .build();

        Claim savedClaim = claimRepository.save(claim);
        kafkaTemplate.send(CLAIM_TOPIC, "Claim recibido correctamente");
        return savedClaim;
    }

    @Override
    public List<Claim> findAll() {
        return claimRepository.findAll();
    }

    @Override
    public List<Claim> findByPolicyId(Long policyId) {
        return claimRepository.findByPolicyId(policyId);
    }
}
