package com.libertad.mambu.aplication.usecases

import com.libertad.mambu.domain.model.Client
import com.libertad.mambu.domain.port.`in`.CreateClientUseCase
import com.libertad.mambu.domain.port.out.RemoteClientServicePort


class CreateClientUseCaseImpl(private val remoteClientServicePort: RemoteClientServicePort): CreateClientUseCase {

    override fun createClient(data: Client): Client? {
       return remoteClientServicePort.createClient(data)
    }


}