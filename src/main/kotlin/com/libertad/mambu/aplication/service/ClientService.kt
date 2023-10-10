package com.libertad.mambu.aplication.service

import com.libertad.mambu.domain.port.out.RemoteClientServicePort

class ClientService(var remoteClientServicePort: RemoteClientServicePort) {

   fun createClient(data: HashMap<String, Any>): HashMap<String, Any> {
       return remoteClientServicePort.createClient(data)
   }
}