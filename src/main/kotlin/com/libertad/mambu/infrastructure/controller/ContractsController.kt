package com.libertad.mambu.infrastructure.controller

import com.libertad.mambu.aplication.service.ContractsService
import com.libertad.mambu.aplication.service.DepositAccountService
import org.apache.hc.core5.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/contracts")
class ContractsController(private val contractsService: ContractsService) {

    @PostMapping
    fun createContract(@RequestBody data: HashMap<String, Any>): ResponseEntity<HashMap<String, Any>> {
        return ResponseEntity.status(HttpStatus.SC_CREATED).body(contractsService.createContract(data));
    }

}