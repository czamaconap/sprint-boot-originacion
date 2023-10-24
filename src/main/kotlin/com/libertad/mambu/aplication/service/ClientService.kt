package com.libertad.mambu.aplication.service

import com.libertad.mambu.domain.model.Client
import com.libertad.mambu.domain.port.`in`.CreateClientUseCase

class ClientService(
    private val createClientUseCase: CreateClientUseCase):
    CreateClientUseCase {
    override fun createClient(data: Client): Client? {
       return createClientUseCase.createClient(data)
    }


}