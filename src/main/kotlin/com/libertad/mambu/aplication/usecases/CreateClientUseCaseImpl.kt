package com.libertad.mambu.aplication.usecases

import com.libertad.mambu.domain.model.Client
import com.libertad.mambu.domain.port.`in`.CreateClientUseCase
import com.libertad.mambu.domain.port.out.RemoteClientServicePort
import com.libertad.mambu.infrastructure.adapter.RemoteClient
import org.springframework.http.ResponseEntity


class CreateClientUseCaseImpl(private val remoteClientServicePort: RemoteClientServicePort): CreateClientUseCase {

    override fun createClient(data: Client): ResponseEntity<RemoteClient> {
       return remoteClientServicePort.createClient(data)
    }


}