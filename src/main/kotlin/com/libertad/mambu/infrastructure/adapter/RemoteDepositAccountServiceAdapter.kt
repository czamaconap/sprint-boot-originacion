package com.libertad.mambu.infrastructure.adapter

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
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

    override fun approveDepositAccount(data: HashMap<String, Any>, idAccount: String): HashMap<String, Any> {
        val url = "${configParams.API_URL}/api/deposits/${idAccount}:changeState"
        println(url)

        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
            set("Accept", "application/vnd.mambu.v2+json")
            set("X-IBM-Client-Id", configParams.API_CLIENT_ID)
            set("X-IBM-Client-Secret", configParams.API_CLIENT_SECRET)
        }

        val request = HttpEntity(data, headers)
        val response = restTemplate.postForObject(url, request, HashMap::class.java)
        return if(response.isNullOrEmpty()){
            HashMap()
        }else{
            response as HashMap<String, Any>
        }
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

data class RemoteDepositAccount(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("name")
    var name:  String? = null,
    @SerializedName("accountHolderType")
    var accountHolderType:  String? = null,
    @SerializedName("accountHolderKey")
    var accountHolderKey:  String? = null,
    @SerializedName("productTypeKey")
    var productTypeKey:  String? = null,
    @SerializedName("accountType")
    var accountType:  String? = null,
    @SerializedName("currencyCode")
    var currencyCode: String? = null,
    @SerializedName("assignedBranchKey")
    var assignedBranchKey:  String? = null,
    @SerializedName("interestSettings")
    var interestSettings: RemoteInterestSettings? = null,
    @SerializedName("_CBE_INTER")
    var cbeInter: RemoteCBEInter? = null
)

data class RemoteInterestSettings(
    @SerializedName("interestRateSettings")
    var interestRateSettings: RemoteInterestRateSettings? = null,
    @SerializedName("interestPaymentSettings")
    var interestPaymentSettings: RemoteInterestPaymentSettings? = null
)

data class RemoteInterestRateSettings(
    @SerializedName("encodedKey")
    var encodedKey: String? = null,
    @SerializedName("interestChargeFrequency")
    var interestChargeFrequency: String? = null,
    @SerializedName("interestChargeFrequencyCount")
    var interestChargeFrequencyCount: Int? = null,
    @SerializedName("interestRateTiers")
    var interestRateTiers: List<RemoteInterestRateTiers>? = null,
    @SerializedName("interestRateTerms")
    var interestRateTerms: String? = null,
    @SerializedName("interestRateSource")
    var interestRateSource: String? = null,
)

data class RemoteInterestPaymentSettings(
    @SerializedName("interestPaymentPoint")
    var interestPaymentPoint: String? = null,
    @SerializedName("interestPaymentDates")
    var interestPaymentDates: List<Any>? = emptyList()
)

data class RemoteInterestRateTiers(
    @SerializedName("encodedKey")
    var encodedKey: String? = null,
    @SerializedName("endingBalance")
    var endingBalance: Double? = 0.0,
    @SerializedName("interestRate")
    var interestRate: Double? = 0.0
)

data class RemoteCBEInter(
    @SerializedName("_CBE_IN")
    var cbeIn: String = "00000000000"
)