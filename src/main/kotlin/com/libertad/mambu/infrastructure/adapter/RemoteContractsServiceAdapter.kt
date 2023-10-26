package com.libertad.mambu.infrastructure.adapter

import com.google.gson.annotations.SerializedName
import com.libertad.mambu.aplication.util.prettyPrint
import com.libertad.mambu.domain.port.out.RemoteContractsServicePort
import com.libertad.mambu.infrastructure.config.ConfigParams
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.*
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate

class RemoteContractsServiceAdapter(
    private val restTemplate: RestTemplate
) : RemoteContractsServicePort {

    @Autowired
    lateinit var configParams: ConfigParams

    @Throws(HttpClientErrorException::class)
    override fun createContract(data: RemoteContractReq): ResponseEntity<RemoteContractRes> {
        val url = "${configParams.API_URL_CONTRATOS}"
        println(url)
        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
            set("Accept","application/json")
            set("X-IBM-Client-Id", configParams.API_CLIENT_ID)
            set("X-IBM-Client-Secret", configParams.API_CLIENT_SECRET)
        }
        val reqArr = arrayListOf(data)
        println(prettyPrint(reqArr))
        val request = HttpEntity(reqArr, headers)
        return restTemplate.exchange(
            url,
            HttpMethod.POST,
            request,
            RemoteContractRes::class.java
        )
    }
}


data class RemoteContractReq(
    @SerializedName("op") var op: String? = "REPLACE",
    @SerializedName("path") var path: HashMap<String,Any>? = HashMap()
)

data class RemoteContractRes(
    @SerializedName("status") var status: String?,
    @SerializedName("code") var code: String?
)