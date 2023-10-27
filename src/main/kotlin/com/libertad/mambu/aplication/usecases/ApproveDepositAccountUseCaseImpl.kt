package com.libertad.mambu.aplication.usecases

import com.google.gson.Gson
import com.libertad.mambu.domain.model.ErrorResponse
import com.libertad.mambu.domain.model.MyCustomException
import com.libertad.mambu.domain.port.`in`.ApproveDepositAccountUseCase
import com.libertad.mambu.domain.port.out.RemoteDepositAccountServicePort
import com.libertad.mambu.infrastructure.adapter.RemoteDepositAccount
import org.springframework.http.ResponseEntity
import org.springframework.web.client.HttpClientErrorException

class ApproveDepositAccountUseCaseImpl (private val remoteDepositAccountServicePort: RemoteDepositAccountServicePort): ApproveDepositAccountUseCase {
    @Throws(Exception::class)
    override fun approveDepositAccount(data: HashMap<String, Any>, idAccount: String): ResponseEntity<RemoteDepositAccount> {
        try {
            return remoteDepositAccountServicePort.approveDepositAccount(data, idAccount)
        }catch (ex: HttpClientErrorException){
            val gson = Gson()
            //val startIndex: Int = inputString.indexOf("{")
            val jsonSubString: String = ex.responseBodyAsString
            val errors = gson.fromJson(jsonSubString, ErrorResponse::class.java)
            throw  MyCustomException(message = ex.message, service="/approveDepositAccount", errors)
        }
    }
}