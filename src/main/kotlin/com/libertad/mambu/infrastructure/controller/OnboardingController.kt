package com.libertad.mambu.infrastructure.controller

import com.libertad.mambu.aplication.service.OnboardingService
import org.apache.hc.core5.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/onboarding")
class OnboardingController(private val onboardingService: OnboardingService) {

    @PostMapping("/init")
    fun initProcess(@RequestBody data: HashMap<String, Any>): ResponseEntity<HashMap<String, Any>> {
        return onboardingService.initProcess(data)
    }

}