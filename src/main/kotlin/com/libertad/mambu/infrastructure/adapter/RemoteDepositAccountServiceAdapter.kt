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
    private var restTemplate: RestTemplate
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
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("accountHolderType")
    var accountHolderType: String?,
    @SerializedName("accountHolderKey")
    var accountHolderKey: String?,
    @SerializedName("productTypeKey")
    var productTypeKey: String?,
    @SerializedName("accountType")
    var accountType: String?,
    @SerializedName("currencyCode")
    var currencyCode: String?,
    @SerializedName("assignedBranchKey")
    var assignedBranchKey: String?,
    @SerializedName("interestSettings")
    var interestSettings: RemoteInterestSettings?,
    @SerializedName("_CBE_INTER") 
    var cbeInter: RemoteCbeInter?
)

data class RemoteInterestSettings(
    @SerializedName("interestRateSettings")
    var interestRateSettings: RemoteInterestRateSettings?,
    @SerializedName("interestPaymentSettings")
    var interestPaymentSettings: RemoteInterestPaymentSettings?
)

data class RemoteInterestRateSettings(
    @SerializedName("encodedKey")
    var encodedKey: String?,
    @SerializedName("interestChargeFrequency")
    var interestChargeFrequency: String?,
    @SerializedName("interestChargeFrequencyCount")
    var interestChargeFrequencyCount: Int?,
    @SerializedName("interestRateTiers")
    var interestRateTiers: List<RemoteInterestRateTier>?,
    @SerializedName("interestRateTerms")
    var interestRateTerms: String?,
    @SerializedName("interestRateSource")
    var interestRateSource: String?
)

data class RemoteInterestRateTier(
    @SerializedName("encodedKey")
    var encodedKey: String?,
    @SerializedName("endingBalance")
    var endingBalance: Double?,
    @SerializedName("interestRate")
    var interestRate: Double?
)

data class RemoteInterestPaymentSettings(
    @SerializedName("interestPaymentPoint")
    var interestPaymentPoint: String?,
    @SerializedName("interestPaymentDates")
    var interestPaymentDates: List<String>?
)

data class RemoteCbeInter(
    @SerializedName("_CBE_IN")
    var cbeIn: String?
)