package com.libertad.mambu.domain.port.out

interface RemoteClientServicePort {

    fun createClient(data: HashMap<String, Any>): HashMap<String, Any>

}