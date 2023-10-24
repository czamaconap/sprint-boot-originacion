package com.libertad.mambu.domain.port.`in`

import com.libertad.mambu.domain.model.Client
import org.springframework.http.ResponseEntity

interface OnboardingUseCase {

    fun initProcess(data:  Client): ResponseEntity<HashMap<String, Any>>
}