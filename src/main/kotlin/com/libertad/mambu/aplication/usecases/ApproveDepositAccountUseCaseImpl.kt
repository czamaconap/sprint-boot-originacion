package com.libertad.mambu.aplication.usecases

import com.libertad.mambu.domain.port.`in`.ApproveDepositAccountUseCase
import com.libertad.mambu.domain.port.out.RemoteDepositAccountServicePort

class ApproveDepositAccountUseCaseImpl (private val remoteDepositAccountServicePort: RemoteDepositAccountServicePort): ApproveDepositAccountUseCase {
    override fun approveDepositAccount(data: HashMap<String, Any>, idAccount: String): HashMap<String, Any> {
        return remoteDepositAccountServicePort.approveDepositAccount(data, idAccount)
    }
}