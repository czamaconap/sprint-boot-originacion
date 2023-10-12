package com.libertad.mambu.aplication.usecases

import com.libertad.mambu.domain.port.`in`.GenerateCBAccountUseCase
import com.libertad.mambu.domain.port.out.RemoteDepositAccountServicePort

class GenerateCBAccountUseCaseImpl (
    private val remoteDepositAccountServicePort: RemoteDepositAccountServicePort):
    GenerateCBAccountUseCase {
    override fun generateCBAccount(data: HashMap<String, Any>): HashMap<String, Any> {
        return remoteDepositAccountServicePort.generateCBAccount(data)
    }

}