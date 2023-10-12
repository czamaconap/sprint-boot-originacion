package com.libertad.mambu.aplication.usecases

import com.libertad.mambu.domain.port.`in`.CreateClientUseCase
import com.libertad.mambu.domain.port.out.RemoteClientServicePort


class CreateClientUseCaseImpl(private val remoteClientServicePort: RemoteClientServicePort): CreateClientUseCase {

    override fun createClient(data: HashMap<String, Any>): HashMap<String, Any> {
       return remoteClientServicePort.createClient(data)
    }


}