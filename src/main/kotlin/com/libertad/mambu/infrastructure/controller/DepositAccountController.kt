package com.libertad.mambu.infrastructure.controller

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.libertad.mambu.aplication.service.ClientService
import com.libertad.mambu.aplication.service.DepositAccountService
import org.apache.hc.core5.http.HttpStatus
import org.springframework.http.ResponseEntity
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

}