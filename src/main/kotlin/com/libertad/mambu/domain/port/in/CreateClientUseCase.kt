package com.libertad.mambu.domain.port.`in`

import com.libertad.mambu.domain.model.Client

interface CreateClientUseCase {

    fun createClient(data: Client): Client?

}
