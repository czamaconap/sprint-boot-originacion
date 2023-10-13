package com.libertad.mambu.aplication.usecases

import com.libertad.mambu.domain.port.`in`.CreateContractUseCase
import com.libertad.mambu.domain.port.out.RemoteContractsServicePort

class CreateContractUseCaseImpl(
    private val remoteContractsServicePort: RemoteContractsServicePort):
    CreateContractUseCase {
    override fun createContract(data: HashMap<String, Any>): HashMap<String, Any> {
       return remoteContractsServicePort.createContract(data)
    }
}