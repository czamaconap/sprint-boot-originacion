package com.libertad.mambu.domain.port.`in`

import org.springframework.http.ResponseEntity

interface UpdateCBAccountUseCase {
    fun updateCBAccount(data: HashMap<String, Any>,  idAccount: String): ResponseEntity<Void>
}