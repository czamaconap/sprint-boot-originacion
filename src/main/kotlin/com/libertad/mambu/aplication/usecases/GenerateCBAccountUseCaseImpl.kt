package com.libertad.mambu.aplication.usecases

import com.libertad.mambu.domain.port.`in`.GenerateCBAccountUseCase
import com.libertad.mambu.domain.port.out.RemoteDepositAccountServicePort
import com.libertad.mambu.infrastructure.adapter.RemoteGenCBAccountReq
import com.libertad.mambu.infrastructure.adapter.RemoteGenCBAccountRes
import org.springframework.http.ResponseEntity

class GenerateCBAccountUseCaseImpl (
    private val remoteDepositAccountServicePort: RemoteDepositAccountServicePort):
    GenerateCBAccountUseCase {
    override fun generateCBAccount(data: RemoteGenCBAccountReq): ResponseEntity<RemoteGenCBAccountRes> {
        return remoteDepositAccountServicePort.generateCBAccount(data)
    }

}