package com.libertad.mambu.domain.port.`in`

import com.libertad.mambu.domain.model.Client
import com.libertad.mambu.infrastructure.adapter.RemoteClient
import org.springframework.http.ResponseEntity
import java.io.IOException

interface CreateClientUseCase {
    @Throws(Exception::class)
    fun createClient(data: Client): ResponseEntity<RemoteClient>

}
