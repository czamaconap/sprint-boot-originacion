package com.libertad.mambu.aplication.usecases

import com.libertad.mambu.domain.port.`in`.ApproveDepositAccountUseCase
import com.libertad.mambu.domain.port.out.RemoteDepositAccountServicePort
import com.libertad.mambu.infrastructure.adapter.RemoteDepositAccount
import org.springframework.http.ResponseEntity

class ApproveDepositAccountUseCaseImpl (private val remoteDepositAccountServicePort: RemoteDepositAccountServicePort): ApproveDepositAccountUseCase {
    override fun approveDepositAccount(data: HashMap<String, Any>, idAccount: String): ResponseEntity<RemoteDepositAccount> {
        return remoteDepositAccountServicePort.approveDepositAccount(data, idAccount)
    }
}