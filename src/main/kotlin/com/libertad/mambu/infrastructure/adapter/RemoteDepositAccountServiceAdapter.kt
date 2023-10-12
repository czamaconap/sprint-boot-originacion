package com.libertad.mambu.infrastructure.adapter

import com.libertad.mambu.domain.port.out.RemoteClientServicePort
import com.libertad.mambu.domain.port.out.RemoteDepositAccountServicePort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate

class RemoteDepositAccountServiceAdapter(
    private val restTemplate: RestTemplate
) : RemoteDepositAccountServicePort {

    override fun createDepositAccount(data: HashMap<String, Any>): HashMap<String, Any> {
        val url = "https://apic-min-gw-gateway-cp4i.apps.cp4i.nopro.libertad.dev/libertad-dev/sandbox/api/deposits"
        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
            set("Accept","application/vnd.mambu.v2+json")
            set("X-IBM-Client-Id", "6778cad6d8edcfa7fed7de5b0bd85965")
            //set("X-IBM-Client-Secret","");
        }

        System.out.println("#################################REQUEST:\n")
        System.out.println(data)
        val request = HttpEntity(data, headers)
        return restTemplate.postForObject(url, request, HashMap::class.java) as HashMap<String, Any>
    }
}