package com.libertad.mambu.infrastructure.adapter

import com.libertad.mambu.domain.port.out.RemoteContractsServicePort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate

class RemoteContractsServiceAdapter(
    private val restTemplate: RestTemplate
) : RemoteContractsServicePort {

    override fun createContract(data: HashMap<String, Any>): HashMap<String, Any> {
        val url = "https://apic-min-gw-gateway-cp4i.apps.cp4i.nopro.libertad.dev/libertad-dev/sandbox/dynamics/v1/contratos"
        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
            set("Accept","application/json")
            set("X-IBM-Client-Id", "6778cad6d8edcfa7fed7de5b0bd85965")
        }
        val request = HttpEntity(data, headers)
        return restTemplate.postForObject(url, request, HashMap::class.java) as HashMap<String, Any>
    }

}