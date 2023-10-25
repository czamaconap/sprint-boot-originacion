package com.libertad.mambu.domain.port.`in`

import com.libertad.mambu.domain.model.DepositAccount

interface CreateDepositAccountUseCase {

    fun createDepositAccount(data: DepositAccount): DepositAccount?
}