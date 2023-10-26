package com.libertad.mambu.domain.port.`in`

import com.libertad.mambu.domain.model.DepositAccount
import com.libertad.mambu.infrastructure.adapter.RemoteDepositAccount
import org.springframework.http.ResponseEntity

interface CreateDepositAccountUseCase {

    fun createDepositAccount(data: DepositAccount): ResponseEntity<RemoteDepositAccount>
}