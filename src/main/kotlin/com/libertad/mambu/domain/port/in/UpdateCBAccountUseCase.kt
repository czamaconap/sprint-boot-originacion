package com.libertad.mambu.domain.port.`in`

interface UpdateCBAccountUseCase {
    fun updateCBAccount(data: HashMap<String, Any>,  idAccount: String): HashMap<String, Any>
}