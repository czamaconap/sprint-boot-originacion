package com.libertad.mambu.domain.port.out

import com.libertad.mambu.domain.model.DepositAccount
import com.libertad.mambu.infrastructure.adapter.RemoteDepositAccount
import com.libertad.mambu.infrastructure.adapter.RemoteGenCBAccountReq
import com.libertad.mambu.infrastructure.adapter.RemoteGenCBAccountRes
import org.springframework.http.ResponseEntity

interface RemoteDepositAccountServicePort {

    fun createDepositAccount(data: DepositAccount): ResponseEntity<RemoteDepositAccount>

    fun updateCBAccount(data: HashMap<String, Any>, idAccount: String): HashMap<String, Any>

    fun generateCBAccount(data: RemoteGenCBAccountReq): ResponseEntity<RemoteGenCBAccountRes>

    fun approveDepositAccount(data: HashMap<String, Any>, idAccount: String): HashMap<String, Any>
}