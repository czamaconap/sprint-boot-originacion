package com.libertad.mambu.aplication.usecases

import com.libertad.mambu.domain.port.`in`.UpdateCBAccountUseCase
import com.libertad.mambu.domain.port.out.RemoteDepositAccountServicePort
import org.springframework.http.ResponseEntity

class UpdateCBAccountUseCaseImpl(
    private val remoteDepositAccountServicePort: RemoteDepositAccountServicePort):
    UpdateCBAccountUseCase {

    override fun updateCBAccount(data: HashMap<String, Any>, idAccount: String): ResponseEntity<Void> {
        return remoteDepositAccountServicePort.updateCBAccount(data, idAccount)
    }
}