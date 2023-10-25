package com.libertad.mambu.infrastructure.adapter

import com.google.gson.annotations.SerializedName
import com.libertad.mambu.aplication.util.prettyPrint
import com.libertad.mambu.domain.model.Client as DomainClient
import com.libertad.mambu.domain.port.out.RemoteClientServicePort
import com.libertad.mambu.infrastructure.config.ConfigParams
import com.libertad.mambu.infrastructure.mapper.ClientMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.*
import org.springframework.web.client.RestTemplate

class RemoteClientServiceAdapter(
    private val restTemplate: RestTemplate
) : RemoteClientServicePort {

    @Autowired
    lateinit var configParams: ConfigParams

    override fun createClient(data: DomainClient): DomainClient? {
        val url = "${configParams.API_URL}/api/clients"
        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
            set("Accept", "application/vnd.mambu.v2+json")
            set("X-IBM-Client-Id", configParams.API_CLIENT_ID)
            set("X-IBM-Client-Secret", configParams.API_CLIENT_SECRET)
        }


        val remoteClient = ClientMapper.mapToRemote(data)

        println(prettyPrint(remoteClient))


        val request = HttpEntity(remoteClient, headers)
        val responseEntity: ResponseEntity<RemoteClient> = restTemplate.exchange(
            url,
            HttpMethod.POST,
            request,
            RemoteClient::class.java
        )
        return responseEntity.body?.let {
            ClientMapper.mapToDomain(it)
        }
    }
}

data class RemoteClient(
    @SerializedName("encodedKey") var encodedKey: String? = null,
    @SerializedName("firstName") var firstName: String? = null,
    @SerializedName("middleName") var middleName: String? = null,
    @SerializedName("lastName") var lastName: String? = null,
    @SerializedName("birthDate") var birthDate: String? = null,
    @SerializedName("mobilePhone") var mobilePhone: String? = null,
    @SerializedName("emailAddress") var emailAddress: String? = null,
    @SerializedName("address") var address: List<RemoteAddress>? = emptyList(),
    @SerializedName("_Clientes_N2") var clientN2: RemoteClientN2? = null,
    @SerializedName("id") var id: String? = null,
)

data class RemoteClientN2(
    @SerializedName("_RFC") var rfc: String? = null,
    @SerializedName("_CURP") var curp: String? = null,
)

data class RemoteAddress(
    @SerializedName("encodedKey") var encodedKey: String? = null,
    @SerializedName("parentKey") var parentKey: String? = null,
    @SerializedName("line1") var line1: String? = null,
    @SerializedName("line2") var line2: String? = null,
    @SerializedName("line3") var line3: String? = null,
    @SerializedName("postcode") var postcode: String? = null,
    @SerializedName("city") var city: String? = null,
    @SerializedName("region") var region: String? = null,
    @SerializedName("country") var country: String? = null,
    @SerializedName("indexInList") var indexInList: Int? = null
)