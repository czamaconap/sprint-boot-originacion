package com.libertad.mambu.infrastructure.controller

import com.libertad.mambu.aplication.service.DepositAccountService
import com.libertad.mambu.domain.model.DepositAccount
import com.libertad.mambu.domain.model.InterestRateTiers
import com.libertad.mambu.domain.model.InterestSettings
import org.apache.hc.core5.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/deposits")
class DepositAccountController(private val depositAccountService: DepositAccountService) {

    @PostMapping
    fun createDepositAccount(@RequestBody data: DepositAccount): ResponseEntity<DepositAccount> {
        return ResponseEntity.status(HttpStatus.SC_CREATED).
        body(depositAccountService.createDepositAccount(data));
    }

    @PatchMapping("/{idAccount}")
    fun updateCBAccount(@RequestBody data: HashMap<String, Any>, @PathVariable idAccount: String): ResponseEntity<HashMap<String, Any>> {
        return ResponseEntity.status(HttpStatus.SC_CREATED).
        body(depositAccountService.updateCBAccount(data, idAccount));
    }

    @PostMapping("/cba")
    fun generateCBAccount(@RequestBody data: HashMap<String, Any>): ResponseEntity<HashMap<String, Any>> {
        return ResponseEntity.status(HttpStatus.SC_CREATED).
        body(depositAccountService.generateCBAccount(data));
    }

}