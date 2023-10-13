package com.libertad.mambu.aplication.service

import com.libertad.mambu.domain.port.`in`.CreateClientUseCase
import com.libertad.mambu.domain.port.`in`.CreateContractUseCase

class ContractsService(
    private val createContractUseCase: CreateContractUseCase): CreateContractUseCase {
    override fun createContract(data: HashMap<String, Any>): HashMap<String, Any> {
        return createContractUseCase.createContract(data)
    }

}