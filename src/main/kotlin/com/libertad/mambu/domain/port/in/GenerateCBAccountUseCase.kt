package com.libertad.mambu.domain.port.`in`

interface GenerateCBAccountUseCase {

    fun generateCBAccount(data: HashMap<String, Any>): HashMap<String, Any>
}