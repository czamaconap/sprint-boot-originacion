package com.libertad.mambu.domain.port.out

interface RemoteDepositAccountServicePort {

    fun createDepositAccount(data: HashMap<String, Any>): HashMap<String, Any>

    fun updateCBAccount(data: HashMap<String, Any>): HashMap<String, Any>

    fun generateCBAccount(data: HashMap<String, Any>): HashMap<String, Any>

}