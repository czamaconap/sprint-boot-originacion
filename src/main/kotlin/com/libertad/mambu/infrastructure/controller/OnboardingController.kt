package com.libertad.mambu.infrastructure.controller

import com.libertad.mambu.aplication.service.OnboardingService
import com.libertad.mambu.domain.dto.ClientRequest
import com.libertad.mambu.domain.mapper.ClientMapper

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/onboarding")
class OnboardingController(private val onboardingService: OnboardingService) {

    @PostMapping("/init")
    fun initProcess(@RequestBody data: ClientRequest): ResponseEntity<HashMap<String, Any>> {
        println(ClientMapper.mapToDomain(data))

        return onboardingService.initProcess(ClientMapper.mapToDomain(data))
    }

}