package com.libertad.mambu.aplication.service;

import com.libertad.mambu.domain.port.`in`.CreateClientUseCase
import com.libertad.mambu.domain.port.`in`.OnboardingUseCase

class OnboardingService (private val onboardingUseCase: OnboardingUseCase): OnboardingUseCase {
    override fun initProcess(data: HashMap<String, Any>): HashMap<String, Any> {
        return onboardingUseCase.initProcess(data)
    }


}
