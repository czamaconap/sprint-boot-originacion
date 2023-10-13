package com.libertad.mambu.domain.port.out

interface RemoteContractsServicePort {

    fun createContract(data: HashMap<String, Any>): HashMap<String, Any>


}