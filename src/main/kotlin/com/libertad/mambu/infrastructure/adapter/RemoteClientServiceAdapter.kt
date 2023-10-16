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
       // val url = "https://apic-min-gw-gateway-cp4i.apps.cp4i.nopro.libertad.dev/libertad-qa/sandbox/api/clients"
        val url = "https://apic-min-gw-gateway-cp4i.apps.cp4i.nopro.libertad.dev/libertad-dev/sandbox/api/clients"

        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
            set("Accept","application/vnd.mambu.v2+json")
           // set("X-IBM-Client-Id", "6778cad6d8edcfa7fed7de5b0bd85965")
            set("X-IBM-Client-Id", "7740f10fecadbc3b24aa99247ef4e2c4")
            set("X-IBM-Client-Secret", "59198aeb63a2964cb8028629e4671276")
            //set("X-IBM-Client-Secret","");
        }
        val request = HttpEntity(data, headers)
        return restTemplate.postForObject(url, request, HashMap::class.java) as HashMap<String, Any>
    }
}