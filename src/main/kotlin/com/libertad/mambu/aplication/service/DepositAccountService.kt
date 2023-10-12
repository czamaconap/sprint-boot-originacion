package com.libertad.mambu.aplication.service

import com.libertad.mambu.domain.port.`in`.CreateDepositAccountUseCase
import com.libertad.mambu.domain.port.`in`.GenerateCBAccountUseCase
import com.libertad.mambu.domain.port.`in`.UpdateCBAccountUseCase

class DepositAccountService(
    private val createDepositAccountUseCase: CreateDepositAccountUseCase,
    private val generateCBAccountUseCase: GenerateCBAccountUseCase,
    private val updateCBAccountUseCase: UpdateCBAccountUseCase
):
    CreateDepositAccountUseCase,
    GenerateCBAccountUseCase,
    UpdateCBAccountUseCase
{
    override fun createDepositAccount(data: HashMap<String, Any>): HashMap<String, Any> {
        return createDepositAccountUseCase.createDepositAccount(data)
    }

    override fun generateCBAccount(data: HashMap<String, Any>): HashMap<String, Any> {
        return generateCBAccountUseCase.generateCBAccount(data)
    }

    override fun updateCBAccount(data: HashMap<String, Any>): HashMap<String, Any> {
        return updateCBAccountUseCase.updateCBAccount(data)
    }
}