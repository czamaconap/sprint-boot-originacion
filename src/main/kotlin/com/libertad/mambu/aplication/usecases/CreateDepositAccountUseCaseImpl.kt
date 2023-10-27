package com.libertad.mambu.aplication.usecases

import com.google.gson.Gson
import com.libertad.mambu.domain.model.DepositAccount
import com.libertad.mambu.domain.model.ErrorResponse
import com.libertad.mambu.domain.model.MyCustomException
import com.libertad.mambu.domain.port.`in`.CreateDepositAccountUseCase
import com.libertad.mambu.domain.port.out.RemoteDepositAccountServicePort
import com.libertad.mambu.infrastructure.adapter.RemoteDepositAccount
import org.springframework.http.ResponseEntity
import org.springframework.web.client.HttpClientErrorException

class CreateDepositAccountUseCaseImpl (private val remoteDepositAccountServicePort: RemoteDepositAccountServicePort):
    CreateDepositAccountUseCase {

    @Throws(Exception::class)
    override fun createDepositAccount(data: DepositAccount): ResponseEntity<RemoteDepositAccount> {
        try {
            return remoteDepositAccountServicePort.createDepositAccount(data)
        }catch (ex: HttpClientErrorException){
            val gson = Gson()
            //val startIndex: Int = inputString.indexOf("{")
            val jsonSubString: String = ex.responseBodyAsString
            val errors = gson.fromJson(jsonSubString, ErrorResponse::class.java)
            throw  MyCustomException(message = ex.message, service="/deposits", errors)
        }
    }

}