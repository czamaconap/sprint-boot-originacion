package com.libertad.mambu.domain.port.`in`

interface ApproveDepositAccountUseCase {
    fun approveDepositAccount(data: HashMap<String, Any>, idAccount: String): HashMap<String, Any>
}