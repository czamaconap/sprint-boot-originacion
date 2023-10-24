package com.libertad.mambu.domain.port.out

import com.libertad.mambu.domain.model.Client

interface RemoteClientServicePort {

    fun createClient(data: Client): Client?

}