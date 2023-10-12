package com.libertad.mambu.infrastructure.controller

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.libertad.mambu.aplication.service.ClientService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/client")
class ClientController(private val clientService: ClientService) {

    @PostMapping
    fun createClient(@RequestBody data: HashMap<String, Any>): HashMap<String, Any> {
        return clientService.createClient(data)
    }

}