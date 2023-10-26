package com.libertad.mambu.aplication.service

import com.libertad.mambu.domain.model.DepositAccount
import com.libertad.mambu.domain.port.`in`.ApproveDepositAccountUseCase
import com.libertad.mambu.domain.port.`in`.CreateDepositAccountUseCase
import com.libertad.mambu.domain.port.`in`.GenerateCBAccountUseCase
import com.libertad.mambu.domain.port.`in`.UpdateCBAccountUseCase
import com.libertad.mambu.infrastructure.adapter.RemoteDepositAccount
import com.libertad.mambu.infrastructure.adapter.RemoteGenCBAccountReq
import com.libertad.mambu.infrastructure.adapter.RemoteGenCBAccountRes
import org.springframework.http.ResponseEntity

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
    override fun createDepositAccount(data: DepositAccount):
            ResponseEntity<RemoteDepositAccount> {
        return createDepositAccountUseCase.createDepositAccount(data)
    }

    override fun generateCBAccount(data: RemoteGenCBAccountReq): ResponseEntity<RemoteGenCBAccountRes> {
        return generateCBAccountUseCase.generateCBAccount(data)
    }

    override fun updateCBAccount(data: HashMap<String, Any>,  idAccount: String): ResponseEntity<Void> {
        return updateCBAccountUseCase.updateCBAccount(data, idAccount)
    }

    override fun approveDepositAccount(data: HashMap<String, Any>, idAccount: String): ResponseEntity<RemoteDepositAccount> {
        return approveDepositAccountUseCase.approveDepositAccount(data, idAccount)
    }
}