package com.libertad.mambu.infrastructure.adapter

import com.libertad.mambu.domain.model.DepositAccount
import com.libertad.mambu.domain.port.out.RemoteDepositAccountServicePort
import org.springframework.http.*
import org.springframework.web.client.RestTemplate

class RemoteDepositAccountServiceAdapter(
    private val restTemplate: RestTemplate
) : RemoteDepositAccountServicePort {

    override fun createDepositAccount(data: DepositAccount): HashMap<String, Any> {
        val url = "https://apic-min-gw-gateway-cp4i.apps.cp4i.nopro.libertad.dev/libertad-dev/sandbox/api/deposits"
        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
            set("Accept","application/vnd.mambu.v2+json")
            set("X-IBM-Client-Id", "7740f10fecadbc3b24aa99247ef4e2c4")
            set("X-IBM-Client-Secret", "59198aeb63a2964cb8028629e4671276")
            //set("X-IBM-Client-Secret","");
        }
        val request = HttpEntity(data, headers)
        val responseEntity: ResponseEntity<java.util.HashMap<*, *>> =
            restTemplate.exchange(url, HttpMethod.POST, request, HashMap::class.java)

        return responseEntity.body as HashMap<String, Any>
        // restTemplate.postForObject(url, request, DepositAccount::class.java) as HashMap<String, Any>
    }

    override fun generateCBAccount(data: HashMap<String, Any>): HashMap<String, Any> {
        val url = "https://apic-min-gw-gateway-cp4i.apps.cp4i.nopro.libertad.dev/libertad-dev/sandbox/frame-banking/generactacb"
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


    override fun updateCBAccount(data: HashMap<String, Any>): HashMap<String, Any> {
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
        return restTemplate.patchForObject(url, request, HashMap::class.java) as HashMap<String, Any>
    }
}