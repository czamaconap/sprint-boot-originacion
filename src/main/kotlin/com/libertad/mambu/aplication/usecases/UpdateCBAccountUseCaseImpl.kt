package com.libertad.mambu.aplication.usecases

import com.libertad.mambu.domain.port.`in`.UpdateCBAccountUseCase
import com.libertad.mambu.domain.port.out.RemoteDepositAccountServicePort

class UpdateCBAccountUseCaseImpl(
    private val remoteDepositAccountServicePort: RemoteDepositAccountServicePort):
    UpdateCBAccountUseCase {
    override fun updateCBAccount(data: HashMap<String, Any>): HashMap<String, Any> {
        return remoteDepositAccountServicePort.updateCBAccount(data)
    }
}