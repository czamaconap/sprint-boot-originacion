package com.libertad.mambu.infrastructure.controller

import com.libertad.mambu.aplication.service.ContractsService
import com.libertad.mambu.aplication.service.DepositAccountService
import com.libertad.mambu.infrastructure.adapter.RemoteContractReq
import com.libertad.mambu.infrastructure.adapter.RemoteContractRes
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
    fun createContract(@RequestBody data: RemoteContractReq): ResponseEntity<RemoteContractRes> {
        return contractsService.createContract(data);
    }

}