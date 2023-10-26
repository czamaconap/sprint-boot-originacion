package com.libertad.mambu.aplication.usecases

import com.libertad.mambu.domain.model.DepositAccount
import com.libertad.mambu.domain.port.`in`.CreateDepositAccountUseCase
import com.libertad.mambu.domain.port.out.RemoteDepositAccountServicePort
import com.libertad.mambu.infrastructure.adapter.RemoteDepositAccount
import org.springframework.http.ResponseEntity

class CreateDepositAccountUseCaseImpl (private val remoteDepositAccountServicePort: RemoteDepositAccountServicePort):
    CreateDepositAccountUseCase {

    override fun createDepositAccount(data: DepositAccount): ResponseEntity<RemoteDepositAccount> {
        return remoteDepositAccountServicePort.createDepositAccount(data)
    }

}