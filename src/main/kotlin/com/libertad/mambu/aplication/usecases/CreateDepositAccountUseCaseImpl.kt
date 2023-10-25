package com.libertad.mambu.aplication.usecases

import com.libertad.mambu.domain.model.DepositAccount
import com.libertad.mambu.domain.port.`in`.CreateDepositAccountUseCase
import com.libertad.mambu.domain.port.out.RemoteDepositAccountServicePort

class CreateDepositAccountUseCaseImpl (private val remoteDepositAccountServicePort: RemoteDepositAccountServicePort):
    CreateDepositAccountUseCase {

    override fun createDepositAccount(data: DepositAccount): DepositAccount? {
        return remoteDepositAccountServicePort.createDepositAccount(data)
    }

}