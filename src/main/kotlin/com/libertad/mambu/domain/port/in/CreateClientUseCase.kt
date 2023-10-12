package com.libertad.mambu.domain.port.`in`

interface CreateClientUseCase {

    fun createClient(data: HashMap<String, Any>): HashMap<String, Any>

}
