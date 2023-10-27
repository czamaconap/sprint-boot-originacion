package com.libertad.mambu.domain.port.`in`

import com.libertad.mambu.infrastructure.adapter.RemoteGenCBAccountReq
import com.libertad.mambu.infrastructure.adapter.RemoteGenCBAccountRes
import org.springframework.http.ResponseEntity
import java.io.IOException

interface GenerateCBAccountUseCase {
    @Throws(Exception::class)
    fun generateCBAccount(data: RemoteGenCBAccountReq): ResponseEntity<RemoteGenCBAccountRes>
}