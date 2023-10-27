package com.libertad.mambu.aplication.usecases

import com.google.gson.Gson
import com.libertad.mambu.domain.model.Client
import com.libertad.mambu.domain.model.ErrorResponse
import com.libertad.mambu.domain.model.MyCustomException
import com.libertad.mambu.domain.port.`in`.CreateClientUseCase
import com.libertad.mambu.domain.port.out.RemoteClientServicePort
import com.libertad.mambu.infrastructure.adapter.RemoteClient
import org.springframework.http.ResponseEntity
import org.springframework.web.client.HttpClientErrorException


class CreateClientUseCaseImpl(private val remoteClientServicePort: RemoteClientServicePort): CreateClientUseCase {

    @Throws(Exception::class)
    override fun createClient(data: Client): ResponseEntity<RemoteClient> {
        try {
            return remoteClientServicePort.createClient(data)
        }catch (ex: HttpClientErrorException){
            val gson = Gson()
            //val startIndex: Int = inputString.indexOf("{")
            val jsonSubString: String = ex.responseBodyAsString
            val errors = gson.fromJson(jsonSubString, ErrorResponse::class.java)
            throw  MyCustomException(message = ex.message, service="/client", errors)
        }
    }


}