package com.libertad.mambu.aplication.service

import com.libertad.mambu.domain.port.`in`.CreateClientUseCase

class ClientService(
    private val createClientUseCase: CreateClientUseCase):
    CreateClientUseCase {
    override fun createClient(data: HashMap<String, Any>): HashMap<String, Any> {
       return createClientUseCase.createClient(data)
    }


}