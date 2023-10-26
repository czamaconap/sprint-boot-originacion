package com.libertad.mambu.domain.port.`in`

import com.libertad.mambu.infrastructure.adapter.RemoteContractReq
import com.libertad.mambu.infrastructure.adapter.RemoteContractRes
import org.springframework.http.ResponseEntity

interface CreateContractUseCase {

    fun createContract(data: RemoteContractReq): ResponseEntity<RemoteContractRes>
}