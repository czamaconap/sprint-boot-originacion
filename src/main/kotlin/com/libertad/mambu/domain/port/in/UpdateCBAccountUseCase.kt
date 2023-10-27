package com.libertad.mambu.domain.port.`in`

import org.springframework.http.ResponseEntity
import java.io.IOException

interface UpdateCBAccountUseCase {
    @Throws(Exception::class)
    fun updateCBAccount(data: HashMap<String, Any>,  idAccount: String): ResponseEntity<Void>
}