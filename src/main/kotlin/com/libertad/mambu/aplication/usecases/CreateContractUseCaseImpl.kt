package com.libertad.mambu.aplication.usecases

import com.google.gson.Gson
import com.libertad.mambu.domain.model.ErrorResponse
import com.libertad.mambu.domain.model.MyCustomException
import com.libertad.mambu.domain.port.`in`.CreateContractUseCase
import com.libertad.mambu.domain.port.out.RemoteContractsServicePort
import com.libertad.mambu.infrastructure.adapter.RemoteContractReq
import com.libertad.mambu.infrastructure.adapter.RemoteContractRes
import org.springframework.http.ResponseEntity
import org.springframework.web.client.HttpClientErrorException

class CreateContractUseCaseImpl(
    private val remoteContractsServicePort: RemoteContractsServicePort):
    CreateContractUseCase {
    @Throws(Exception::class)
    override fun createContract(data: RemoteContractReq): ResponseEntity<RemoteContractRes> {
        try {
            return remoteContractsServicePort.createContract(data)
        }catch (ex: HttpClientErrorException){
            val gson = Gson()
            //val startIndex: Int = inputString.indexOf("{")
            val jsonSubString: String = ex.responseBodyAsString
            val errors = gson.fromJson(jsonSubString, ErrorResponse::class.java)
            throw  MyCustomException(message = ex.message, service="/contracts", errors)
        }
    }
}