package com.libertad.mambu.aplication.service;

import com.libertad.mambu.domain.port.`in`.CreateClientUseCase
import com.libertad.mambu.domain.port.`in`.OnboardingUseCase
import org.springframework.http.ResponseEntity

class OnboardingService (private val onboardingUseCase: OnboardingUseCase): OnboardingUseCase {
    override fun initProcess(data: HashMap<String, Any>): ResponseEntity<HashMap<String, Any>> {
        return onboardingUseCase.initProcess(data)
    }


}
