package com.libertad.mambu.domain.port.out

import com.libertad.mambu.domain.model.Product

interface RemoteDepositAccountServicePort {

    fun createDepositAccount(data: HashMap<String, Any>): HashMap<String, Any>

}