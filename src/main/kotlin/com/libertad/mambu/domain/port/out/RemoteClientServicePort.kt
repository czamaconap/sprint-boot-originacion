package com.libertad.mambu.domain.port.out

import com.libertad.mambu.domain.model.Product

interface RemoteClientServicePort {

    fun createClient(data: HashMap<String, Any>): HashMap<String, Any>

}