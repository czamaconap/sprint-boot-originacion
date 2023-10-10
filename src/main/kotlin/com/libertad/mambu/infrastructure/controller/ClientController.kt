package com.libertad.mambu.infrastructure.controller

import com.libertad.mambu.aplication.service.ClientService
import com.libertad.mambu.domain.port.out.RemoteClientServicePort
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/client")
class ClientController(private val clientService: ClientService) {

    @PostMapping
    fun createClient(data: HashMap<String, Any>): HashMap<String, Any> {
        return clientService.createClient(data)
    }

}