package com.libertad.mambu.infrastructure.controller

import com.libertad.mambu.aplication.service.ClientService
import com.libertad.mambu.domain.dto.ClientRequest
import com.libertad.mambu.domain.mapper.ClientMapper
import com.libertad.mambu.domain.model.Client
import org.apache.hc.core5.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/clients")
class ClientController(private val clientService: ClientService) {

    @PostMapping
    fun createClient(@RequestBody data: ClientRequest): ResponseEntity<Client> {
        return ResponseEntity.status(HttpStatus.SC_CREATED)
            .body(clientService.createClient(ClientMapper.mapToDomain(data)));
    }

}