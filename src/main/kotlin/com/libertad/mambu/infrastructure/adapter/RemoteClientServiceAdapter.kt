package com.libertad.mambu.infrastructure.adapter

import com.libertad.mambu.domain.port.out.RemoteClientServicePort
import com.libertad.mambu.infrastructure.config.ConfigParams
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate

class RemoteClientServiceAdapter(
    private val restTemplate: RestTemplate
) : RemoteClientServicePort {

    @Autowired
    lateinit var configParams: ConfigParams

    override fun createClient(data: HashMap<String, Any>): HashMap<String, Any> {
        val url = "${configParams.API_URL}/api/clients"
        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
            set("Accept","application/vnd.mambu.v2+json")
            set("X-IBM-Client-Id", configParams.API_CLIENT_ID)
            set("X-IBM-Client-Secret", configParams.API_CLIENT_SECRET)
        }
        val request = HttpEntity(data, headers)
        return restTemplate.postForObject(url, request, HashMap::class.java) as HashMap<String, Any>
    }
}