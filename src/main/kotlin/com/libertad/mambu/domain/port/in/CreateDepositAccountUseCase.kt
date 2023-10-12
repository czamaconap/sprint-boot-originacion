package com.libertad.mambu.domain.port.`in`

interface CreateDepositAccountUseCase {

    fun createDepositAccount(data: HashMap<String, Any>): HashMap<String, Any>
}