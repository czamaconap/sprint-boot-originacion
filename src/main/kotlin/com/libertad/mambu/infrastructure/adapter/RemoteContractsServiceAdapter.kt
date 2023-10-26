package com.libertad.mambu.infrastructure.adapter

import com.libertad.mambu.domain.port.out.RemoteContractsServicePort
import com.libertad.mambu.infrastructure.config.ConfigParams
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate

class RemoteContractsServiceAdapter(
    private val restTemplate: RestTemplate
) : RemoteContractsServicePort {

    @Autowired
    lateinit var configParams: ConfigParams

    @Throws(HttpClientErrorException::class)
    override fun createContract(data: HashMap<String, Any>): HashMap<String, Any> {
        val url = "${configParams.API_URL_CONTRATOS}"
        println(url)
        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
            set("Accept","application/json")
            set("X-IBM-Client-Id", configParams.API_CLIENT_ID)
            set("X-IBM-Client-Secret", configParams.API_CLIENT_SECRET)
        }
        val request = HttpEntity(data, headers)
        val response = restTemplate.postForObject(url, request, HashMap::class.java)
        if(response.isNullOrEmpty()){
            return HashMap()
        }else{
            return response as HashMap<String, Any>
        }
    }
}