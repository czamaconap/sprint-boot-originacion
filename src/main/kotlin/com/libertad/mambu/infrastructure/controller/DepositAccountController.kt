package com.libertad.mambu.infrastructure.controller

import com.libertad.mambu.aplication.service.DepositAccountService
import com.libertad.mambu.domain.model.DepositAccount
import com.libertad.mambu.infrastructure.adapter.RemoteDepositAccount
import com.libertad.mambu.infrastructure.adapter.RemoteGenCBAccountReq
import com.libertad.mambu.infrastructure.adapter.RemoteGenCBAccountRes
import org.apache.hc.core5.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/deposits")
class DepositAccountController(private val depositAccountService: DepositAccountService) {

    @PostMapping
    fun createDepositAccount(@RequestBody data: DepositAccount): ResponseEntity<RemoteDepositAccount> {
        return depositAccountService.createDepositAccount(data);
    }

    @PatchMapping("/{idAccount}")
    fun updateCBAccount(@RequestBody data: HashMap<String, Any>, @PathVariable idAccount: String): ResponseEntity<Void> {
        return depositAccountService.updateCBAccount(data, idAccount);
    }

    @PostMapping("/cba")
    fun generateCBAccount(@RequestBody data: RemoteGenCBAccountReq): ResponseEntity<RemoteGenCBAccountRes> {
        return depositAccountService.generateCBAccount(data);
    }

    @PostMapping("/prueba")
    fun prueba(@RequestBody data: DepositAccount): ResponseEntity<DepositAccount> {
        return ResponseEntity.status(HttpStatus.SC_CREATED).
        body(data);
    }

}