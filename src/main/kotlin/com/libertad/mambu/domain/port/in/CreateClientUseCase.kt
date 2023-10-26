package com.libertad.mambu.domain.port.`in`

import com.libertad.mambu.domain.model.Client
import com.libertad.mambu.infrastructure.adapter.RemoteClient
import org.springframework.http.ResponseEntity

interface CreateClientUseCase {

    fun createClient(data: Client): ResponseEntity<RemoteClient>

}
