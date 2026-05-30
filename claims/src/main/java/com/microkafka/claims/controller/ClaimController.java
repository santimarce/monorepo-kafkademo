package com.microkafka.claims.controller;

import com.microkafka.claims.dto.ClaimDto;
import com.microkafka.claims.model.Claim;
import com.microkafka.claims.service.ClaimService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {
    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Claim create(@RequestBody ClaimDto claimDto) {
        return claimService.create(claimDto);
    }

    @GetMapping
    public List<Claim> findAll() {
        return claimService.findAll();
    }

    @GetMapping("/policy/{policyId}")
    public List<Claim> findByPolicyId(@PathVariable Long policyId) {
        return claimService.findByPolicyId(policyId);
    }
}
