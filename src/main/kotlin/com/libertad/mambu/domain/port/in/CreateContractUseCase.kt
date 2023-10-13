package com.libertad.mambu.domain.port.`in`

interface CreateContractUseCase {

    fun createContract(data: HashMap<String, Any>): HashMap<String, Any>
}