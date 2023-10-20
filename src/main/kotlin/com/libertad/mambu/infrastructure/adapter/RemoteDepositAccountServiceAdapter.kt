package com.libertad.mambu.infrastructure.adapter

import com.google.gson.Gson
import com.libertad.mambu.aplication.util.prettyPrint
import com.libertad.mambu.domain.model.DepositAccount
import com.libertad.mambu.domain.port.out.RemoteDepositAccountServicePort
import com.libertad.mambu.infrastructure.config.ConfigParams
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*
import org.springframework.web.client.RestTemplate

class RemoteDepositAccountServiceAdapter(
    private val restTemplate: RestTemplate
) : RemoteDepositAccountServicePort {

    @Autowired
    lateinit var configParams: ConfigParams

    override fun createDepositAccount(data: DepositAccount): HashMap<String, Any> {
        val url = "${configParams.API_URL}/api/deposits"
        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
            set("Accept", "application/vnd.mambu.v2+json")
            set("X-IBM-Client-Id", configParams.API_CLIENT_ID)
            set("X-IBM-Client-Secret", configParams.API_CLIENT_SECRET)
        }
        val request = HttpEntity(data, headers)
        val responseEntity: ResponseEntity<java.util.HashMap<*, *>> =
            restTemplate.exchange(url, HttpMethod.POST, request, HashMap::class.java)
        return responseEntity.body as HashMap<String, Any>
    }

    override fun generateCBAccount(data: HashMap<String, Any>): HashMap<String, Any> {
        val url = "${configParams.API_URL}/frame-banking/generactacb"
        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
            set("X-IBM-Client-Id", configParams.API_CLIENT_ID)
            set("X-IBM-Client-Secret", configParams.API_CLIENT_SECRET)
        }
        val request = HttpEntity(data, headers)
        return restTemplate.postForObject(url, request, HashMap::class.java) as HashMap<String, Any>
    }

    override fun updateCBAccount(data: HashMap<String, Any>, idAccount: String): HashMap<String, Any> {
        val url = "${configParams.API_URL}/api/deposits/${idAccount}"
        println(url)

        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
            set("Accept", "application/vnd.mambu.v2+json")
            set("X-IBM-Client-Id", configParams.API_CLIENT_ID)
            set("X-IBM-Client-Secret", configParams.API_CLIENT_SECRET)
        }
        val reqArr = arrayListOf(data)
        println(prettyPrint(reqArr))
        val request = HttpEntity(reqArr, headers)
        val response = restTemplate.patchForObject(url, request, HashMap::class.java)
        if(response.isNullOrEmpty()){
            return HashMap()
        }else{
            return response as HashMap<String, Any>
        }
    }
}