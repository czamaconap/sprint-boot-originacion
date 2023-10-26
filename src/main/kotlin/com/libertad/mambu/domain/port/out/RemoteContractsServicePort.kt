package com.libertad.mambu.domain.port.out

import com.libertad.mambu.infrastructure.adapter.RemoteContractReq
import com.libertad.mambu.infrastructure.adapter.RemoteContractRes
import org.springframework.http.ResponseEntity

interface RemoteContractsServicePort {

    fun createContract(data: RemoteContractReq): ResponseEntity<RemoteContractRes>


}