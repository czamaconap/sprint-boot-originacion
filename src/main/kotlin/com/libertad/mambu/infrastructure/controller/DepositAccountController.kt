package com.libertad.mambu.infrastructure.controller

import com.libertad.mambu.aplication.service.DepositAccountService
import org.apache.hc.core5.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/deposits")
class DepositAccountController(private val depositAccountService: DepositAccountService) {

    @PostMapping
    fun createDepositAccount(@RequestBody data: HashMap<String, Any>): ResponseEntity<HashMap<String, Any>> {
        return ResponseEntity.status(HttpStatus.SC_CREATED).
        body(depositAccountService.createDepositAccount(data));
    }

    @PatchMapping
    fun updateCBAccount(@RequestBody data: HashMap<String, Any>): ResponseEntity<HashMap<String, Any>> {
        return ResponseEntity.status(HttpStatus.SC_CREATED).
        body(depositAccountService.updateCBAccount(data));
    }

    @PatchMapping("/cba")
    fun generateCBAccount(@RequestBody data: HashMap<String, Any>): ResponseEntity<HashMap<String, Any>> {
        return ResponseEntity.status(HttpStatus.SC_CREATED).
        body(depositAccountService.generateCBAccount(data));
    }

}