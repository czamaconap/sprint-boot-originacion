package com.libertad.mambu.aplication.service

import com.libertad.mambu.domain.model.DepositAccount
import com.libertad.mambu.domain.port.`in`.ApproveDepositAccountUseCase
import com.libertad.mambu.domain.port.`in`.CreateDepositAccountUseCase
import com.libertad.mambu.domain.port.`in`.GenerateCBAccountUseCase
import com.libertad.mambu.domain.port.`in`.UpdateCBAccountUseCase

class DepositAccountService(
    private val createDepositAccountUseCase: CreateDepositAccountUseCase,
    private val generateCBAccountUseCase: GenerateCBAccountUseCase,
    private val updateCBAccountUseCase: UpdateCBAccountUseCase,
    private val approveDepositAccountUseCase: ApproveDepositAccountUseCase
):
    CreateDepositAccountUseCase,
    GenerateCBAccountUseCase,
    UpdateCBAccountUseCase,
    ApproveDepositAccountUseCase
{
    override fun createDepositAccount(data: DepositAccount): DepositAccount? {
        return createDepositAccountUseCase.createDepositAccount(data)
    }

    override fun generateCBAccount(data: HashMap<String, Any>): HashMap<String, Any> {
        return generateCBAccountUseCase.generateCBAccount(data)
    }

    override fun updateCBAccount(data: HashMap<String, Any>,  idAccount: String): HashMap<String, Any> {
        return updateCBAccountUseCase.updateCBAccount(data, idAccount)
    }

    override fun approveDepositAccount(data: HashMap<String, Any>, idAccount: String): HashMap<String, Any> {
        return approveDepositAccountUseCase.approveDepositAccount(data, idAccount)
    }
}