package com.libertad.mambu.aplication.usecases

import com.libertad.mambu.domain.port.`in`.CreateContractUseCase
import com.libertad.mambu.domain.port.out.RemoteContractsServicePort
import com.libertad.mambu.infrastructure.adapter.RemoteContractReq
import com.libertad.mambu.infrastructure.adapter.RemoteContractRes
import org.springframework.http.ResponseEntity

class CreateContractUseCaseImpl(
    private val remoteContractsServicePort: RemoteContractsServicePort):
    CreateContractUseCase {
    override fun createContract(data: RemoteContractReq): ResponseEntity<RemoteContractRes> {
       return remoteContractsServicePort.createContract(data)
    }
}