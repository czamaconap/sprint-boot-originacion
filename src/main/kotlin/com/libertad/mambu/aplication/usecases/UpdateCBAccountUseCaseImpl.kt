package com.libertad.mambu.aplication.usecases

import com.google.gson.Gson
import com.libertad.mambu.domain.model.ErrorResponse
import com.libertad.mambu.domain.model.MyCustomException
import com.libertad.mambu.domain.port.`in`.UpdateCBAccountUseCase
import com.libertad.mambu.domain.port.out.RemoteDepositAccountServicePort
import org.springframework.http.ResponseEntity
import org.springframework.web.client.HttpClientErrorException

class UpdateCBAccountUseCaseImpl(
    private val remoteDepositAccountServicePort: RemoteDepositAccountServicePort):
    UpdateCBAccountUseCase {

    @Throws(Exception::class)
    override fun updateCBAccount(data: HashMap<String, Any>, idAccount: String): ResponseEntity<Void> {
        try {
            return remoteDepositAccountServicePort.updateCBAccount(data, idAccount)
        } catch (ex: HttpClientErrorException) {
            val gson = Gson()
            //val startIndex: Int = inputString.indexOf("{")
            val jsonSubString: String = ex.responseBodyAsString
            val errors = gson.fromJson(jsonSubString, ErrorResponse::class.java)
            throw MyCustomException(message = ex.message, service = "/updateCBAccount", errors)
        }
    }
}