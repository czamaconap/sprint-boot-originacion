package com.libertad.mambu.aplication.service

import com.libertad.mambu.aplication.usecases.CreateDepositAccountUseCaseImpl
import com.libertad.mambu.domain.port.`in`.CreateClientUseCase
import com.libertad.mambu.domain.port.`in`.CreateDepositAccountUseCase

class DepositAccountService(private val createDepositAccountUseCase: CreateDepositAccountUseCase ): CreateDepositAccountUseCase {
    override fun createDepositAccount(data: HashMap<String, Any>): HashMap<String, Any> {
        return createDepositAccountUseCase.createDepositAccount(data)
    }
}