package com.libertad.mambu.aplication.service;

import com.libertad.mambu.domain.model.Client
import com.libertad.mambu.domain.port.`in`.OnboardingUseCase
import org.springframework.http.ResponseEntity

class OnboardingService (private val onboardingUseCase: OnboardingUseCase): OnboardingUseCase {
    override fun initProcess(data: Client): ResponseEntity<HashMap<String, Any>> {
        return onboardingUseCase.initProcess(data)
    }

}
