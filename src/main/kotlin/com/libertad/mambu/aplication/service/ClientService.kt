package com.libertad.mambu.aplication.service

import com.libertad.mambu.domain.port.`in`.CreateClientUseCase
import com.libertad.mambu.domain.port.out.RemoteClientServicePort
import org.springframework.stereotype.Service

class ClientService(
    private val createClientUseCase: CreateClientUseCase):
    CreateClientUseCase {
    override fun createClient(data: HashMap<String, Any>): HashMap<String, Any> {
       return createClientUseCase.createClient(data)
    }


}