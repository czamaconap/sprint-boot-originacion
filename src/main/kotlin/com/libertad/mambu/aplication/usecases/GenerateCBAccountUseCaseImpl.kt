package com.libertad.mambu.aplication.usecases

import com.google.gson.Gson
import com.libertad.mambu.domain.model.ErrorResponse
import com.libertad.mambu.domain.model.MyCustomException
import com.libertad.mambu.domain.port.`in`.GenerateCBAccountUseCase
import com.libertad.mambu.domain.port.out.RemoteDepositAccountServicePort
import com.libertad.mambu.infrastructure.adapter.RemoteGenCBAccountReq
import com.libertad.mambu.infrastructure.adapter.RemoteGenCBAccountRes
import org.springframework.http.ResponseEntity
import org.springframework.web.client.HttpClientErrorException

class GenerateCBAccountUseCaseImpl (
    private val remoteDepositAccountServicePort: RemoteDepositAccountServicePort):
    GenerateCBAccountUseCase {
    @Throws(Exception::class)
    override fun generateCBAccount(data: RemoteGenCBAccountReq): ResponseEntity<RemoteGenCBAccountRes> {
        try {
            return remoteDepositAccountServicePort.generateCBAccount(data)
        } catch (ex: HttpClientErrorException) {
            val gson = Gson()
            //val startIndex: Int = inputString.indexOf("{")
            val jsonSubString: String = ex.responseBodyAsString
            val errors = gson.fromJson(jsonSubString, ErrorResponse::class.java)
            throw MyCustomException(message = ex.message, service = "/generateCBAccount", errors)
        }
    }
}