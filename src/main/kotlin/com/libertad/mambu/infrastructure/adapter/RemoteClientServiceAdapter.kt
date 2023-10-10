package com.libertad.mambu.infrastructure.adapter

import com.libertad.mambu.domain.port.out.RemoteClientServicePort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate

class RemoteClientServiceAdapter(
    private val restTemplate: RestTemplate
) : RemoteClientServicePort {

    override fun createClient(data: HashMap<String, Any>): HashMap<String, Any> {
        System.out.println("#createClient#")
        System.out.println("###############################################################################################")
        val url = "https://libertad.sandbox.mambu.com/api/clients"
        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
        }
        val request = HttpEntity(data, headers)
        return restTemplate.postForObject(url, request, HashMap::class.java) as HashMap<String, Any>
    }
}