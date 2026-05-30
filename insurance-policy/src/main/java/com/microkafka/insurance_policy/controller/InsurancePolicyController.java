package com.microkafka.insurance_policy.controller;

import com.microkafka.insurance_policy.dto.InsurancePolicyDto;
import com.microkafka.insurance_policy.model.InsurancePolicy;
import com.microkafka.insurance_policy.service.InsurancePolicyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insurance")
public class InsurancePolicyController {
    private final InsurancePolicyService insurancePolicyService;

    public InsurancePolicyController(InsurancePolicyService insurancePolicyService) {
        this.insurancePolicyService = insurancePolicyService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InsurancePolicy create(@RequestBody InsurancePolicyDto insurancePolicyDto) {
        return insurancePolicyService.create(insurancePolicyDto);
    }

    @GetMapping
    public List<InsurancePolicy> findAll() {
        return insurancePolicyService.findAll();
    }

    @GetMapping("/client/{clientId}")
    public List<InsurancePolicy> findByClientId(@PathVariable Long clientId) {
        return insurancePolicyService.findByClientId(clientId);
    }
}
