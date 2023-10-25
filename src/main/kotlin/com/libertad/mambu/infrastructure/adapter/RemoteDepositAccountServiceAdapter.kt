package com.libertad.mambu.infrastructure.adapter

import com.google.gson.annotations.SerializedName
import com.libertad.mambu.aplication.util.prettyPrint
import com.libertad.mambu.domain.model.DepositAccount
import com.libertad.mambu.domain.port.out.RemoteDepositAccountServicePort
import com.libertad.mambu.infrastructure.config.ConfigParams
import com.libertad.mambu.infrastructure.mapper.DepositAccountMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.*
import org.springframework.web.client.RestTemplate

class RemoteDepositAccountServiceAdapter(
    private val restTemplate: RestTemplate
) : RemoteDepositAccountServicePort {

    @Autowired
    lateinit var configParams: ConfigParams

    override fun createDepositAccount(data: DepositAccount): DepositAccount? {
        val url = "${configParams.API_URL}/api/deposits"
        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
            set("Accept", "application/vnd.mambu.v2+json")
            set("X-IBM-Client-Id", configParams.API_CLIENT_ID)
            set("X-IBM-Client-Secret", configParams.API_CLIENT_SECRET)
        }

        val remoteDepositAccount = DepositAccountMapper.mapToRemote(data)

        println(prettyPrint(data))
        println(prettyPrint(remoteDepositAccount))
        val request = HttpEntity(remoteDepositAccount, headers)
        val responseEntity: ResponseEntity<RemoteDepositAccount> = restTemplate.exchange(
            url,
            HttpMethod.POST,
            request,
            RemoteDepositAccount::class.java
        )
        return responseEntity.body?.let {
            DepositAccountMapper.mapToDomain(it)
        }
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
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("accountHolderType") val accountHolderType: String?,
    @SerializedName("accountHolderKey") val accountHolderKey: String?,
    @SerializedName("productTypeKey") val productTypeKey: String?,
    @SerializedName("accountType") val accountType: String?,
    @SerializedName("currencyCode") val currencyCode: String?,
    @SerializedName("assignedBranchKey") val assignedBranchKey: String?,
    @SerializedName("interestSettings") val interestSettings: RemoteInterestSettings?,
    @SerializedName("_CBE_INTER") val _CBE_INTER: RemoteCbeInter?
)

data class RemoteInterestSettings(
    @SerializedName("interestRateSettings") val interestRateSettings: RemoteInterestRateSettings?,
    @SerializedName("interestPaymentSettings") val interestPaymentSettings: RemoteInterestPaymentSettings?
)

data class RemoteInterestRateSettings(
    @SerializedName("encodedKey") val encodedKey: String?,
    @SerializedName("interestChargeFrequency") val interestChargeFrequency: String?,
    @SerializedName("interestChargeFrequencyCount") val interestChargeFrequencyCount: Int?,
    @SerializedName("interestRateTiers") val interestRateTiers: List<RemoteInterestRateTier>?,
    @SerializedName("interestRateTerms") val interestRateTerms: String?,
    @SerializedName("interestRateSource") val interestRateSource: String?
)

data class RemoteInterestRateTier(
    @SerializedName("encodedKey") val encodedKey: String?,
    @SerializedName("endingBalance") val endingBalance: Double?,
    @SerializedName("interestRate") val interestRate: Double?
)

data class RemoteInterestPaymentSettings(
    @SerializedName("interestPaymentPoint") val interestPaymentPoint: String?,
    @SerializedName("interestPaymentDates") val interestPaymentDates: List<String>?
)

data class RemoteCbeInter(
    @SerializedName("_CBE_IN") val _CBE_IN: String?
)