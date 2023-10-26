package com.libertad.mambu.domain.port.`in`

import com.libertad.mambu.infrastructure.adapter.RemoteDepositAccount
import org.springframework.http.ResponseEntity

interface ApproveDepositAccountUseCase {
    fun approveDepositAccount(data: HashMap<String, Any>, idAccount: String): ResponseEntity<RemoteDepositAccount>
}