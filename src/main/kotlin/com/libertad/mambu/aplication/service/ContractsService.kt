package com.libertad.mambu.aplication.service

import com.libertad.mambu.domain.port.`in`.CreateClientUseCase
import com.libertad.mambu.domain.port.`in`.CreateContractUseCase
import com.libertad.mambu.infrastructure.adapter.RemoteContractReq
import com.libertad.mambu.infrastructure.adapter.RemoteContractRes
import org.springframework.http.ResponseEntity

class ContractsService(
    private val createContractUseCase: CreateContractUseCase): CreateContractUseCase {
    override fun createContract(data: RemoteContractReq): ResponseEntity<RemoteContractRes> {
        return createContractUseCase.createContract(data)
    }

}