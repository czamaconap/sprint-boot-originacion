package com.libertad.mambu.domain.port.`in`

import com.libertad.mambu.infrastructure.adapter.RemoteContractReq
import com.libertad.mambu.infrastructure.adapter.RemoteContractRes
import org.springframework.http.ResponseEntity
import java.io.IOException

interface CreateContractUseCase {
    @Throws(Exception::class)
    fun createContract(data: RemoteContractReq): ResponseEntity<RemoteContractRes>
}