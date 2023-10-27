package com.libertad.mambu.domain.port.`in`

import com.libertad.mambu.infrastructure.adapter.RemoteDepositAccount
import org.springframework.http.ResponseEntity
import java.io.IOException

interface ApproveDepositAccountUseCase {
    @Throws(Exception::class)
    fun approveDepositAccount(data: HashMap<String, Any>, idAccount: String): ResponseEntity<RemoteDepositAccount>
}