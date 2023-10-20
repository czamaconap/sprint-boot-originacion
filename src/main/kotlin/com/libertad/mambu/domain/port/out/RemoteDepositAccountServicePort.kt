package com.libertad.mambu.domain.port.out

import com.libertad.mambu.domain.model.DepositAccount

interface RemoteDepositAccountServicePort {

    fun createDepositAccount(data: DepositAccount): HashMap<String, Any>

    fun updateCBAccount(data: HashMap<String, Any>, idAccount: String): HashMap<String, Any>

    fun generateCBAccount(data: HashMap<String, Any>): HashMap<String, Any>

    fun approveDepositAccount(data: HashMap<String, Any>, idAccount: String): HashMap<String, Any>
}