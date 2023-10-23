package com.libertad.mambu.domain.port.`in`

import org.springframework.http.ResponseEntity

interface OnboardingUseCase {

    fun initProcess(data:  HashMap<String, Any>): ResponseEntity<HashMap<String, Any>>
}