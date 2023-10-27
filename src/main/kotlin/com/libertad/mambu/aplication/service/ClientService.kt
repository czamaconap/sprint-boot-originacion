package com.libertad.mambu.aplication.service

import com.libertad.mambu.domain.model.Client
import com.libertad.mambu.domain.port.`in`.CreateClientUseCase
import com.libertad.mambu.infrastructure.adapter.RemoteClient
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ClientService(
    private val createClientUseCase: CreateClientUseCase):
    CreateClientUseCase {
    override fun createClient(data: Client): ResponseEntity<RemoteClient> {
       return createClientUseCase.createClient(data)
    }


}