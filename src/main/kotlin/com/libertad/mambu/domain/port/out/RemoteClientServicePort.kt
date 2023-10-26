package com.libertad.mambu.domain.port.out

import com.libertad.mambu.domain.model.Client
import com.libertad.mambu.infrastructure.adapter.RemoteClient
import org.springframework.http.ResponseEntity

interface RemoteClientServicePort {

    fun createClient(data: Client): ResponseEntity<RemoteClient>

}