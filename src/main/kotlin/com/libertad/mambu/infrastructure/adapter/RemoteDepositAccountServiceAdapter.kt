package com.libertad.mambu.infrastructure.adapter

import com.google.gson.annotations.SerializedName
import com.libertad.mambu.aplication.util.prettyPrint
import com.libertad.mambu.domain.model.DepositAccount
import com.libertad.mambu.domain.port.out.RemoteDepositAccountServicePort
import com.libertad.mambu.infrastructure.config.ConfigParams
import com.libertad.mambu.infrastructure.mapper.DepositAccountMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.*
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import java.io.IOException

class RemoteDepositAccountServiceAdapter(
    private var restTemplate: RestTemplate
) : RemoteDepositAccountServicePort {

    @Autowired
    lateinit var configParams: ConfigParams

    @Throws(IOException::class)
    override fun createDepositAccount(data: DepositAccount): ResponseEntity<RemoteDepositAccount> {
        val url = "${configParams.API_URL}/api/deposits"
        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
            set("Accept", "application/vnd.mambu.v2+json")
            set("X-IBM-Client-Id", configParams.API_CLIENT_ID)
            set("X-IBM-Client-Secret", configParams.API_CLIENT_SECRET)
        }
        val remoteDepositAccount = DepositAccountMapper.mapToRemote(data)
        return restTemplate.exchange(
            url,
            HttpMethod.POST,
            HttpEntity(remoteDepositAccount, headers),
            RemoteDepositAccount::class.java
        )
    }

    @Throws(IOException::class)
    override fun generateCBAccount(data: RemoteGenCBAccountReq): ResponseEntity<RemoteGenCBAccountRes>  {
        val url = "${configParams.API_URL}/frame-banking/generactacb"
        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
            set("X-IBM-Client-Id", configParams.API_CLIENT_ID)
            set("X-IBM-Client-Secret", configParams.API_CLIENT_SECRET)
        }
        return restTemplate.exchange(
            url,
            HttpMethod.POST,
            HttpEntity(data, headers),
            RemoteGenCBAccountRes::class.java
        )
    }
    @Throws(IOException::class)
    override fun approveDepositAccount(data: HashMap<String, Any>, idAccount: String): ResponseEntity<RemoteDepositAccount> {
        val url = "${configParams.API_URL}/api/deposits/${idAccount}:changeState"
        println(url)

        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
            set("Accept", "application/vnd.mambu.v2+json")
            set("X-IBM-Client-Id", configParams.API_CLIENT_ID)
            set("X-IBM-Client-Secret", configParams.API_CLIENT_SECRET)
        }

        val request = HttpEntity(data, headers)
        /*val response = restTemplate.postForObject(url, request, HashMap::class.java)
        return if (response.isNullOrEmpty()) {
            HashMap()
        } else {
            response as HashMap<String, Any>
        }*/
        return restTemplate.exchange(
            url,
            HttpMethod.POST,
            request,
            RemoteDepositAccount::class.java
        )
    }

    @Throws(IOException::class)
    override fun updateCBAccount(data: HashMap<String, Any>, idAccount: String): ResponseEntity<Void> {
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
        //val response = restTemplate.patchForObject(url, request, HashMap::class.java)
        /*if (response.isNullOrEmpty()) {
            return HashMap()
        } else {
            return response as HashMap<String, Any>
        }*/
        return restTemplate.exchange(
            url,
            HttpMethod.PATCH,
            request,
            Void::class.java
        )
    }
}

data class RemoteDepositAccount(
    @SerializedName("id") var id: String?,
    @SerializedName("name") var name: String?,
    @SerializedName("accountHolderType") var accountHolderType: String?,
    @SerializedName("accountHolderKey") var accountHolderKey: String?,
    @SerializedName("productTypeKey") var productTypeKey: String?,
    @SerializedName("accountType") var accountType: String?,
    @SerializedName("currencyCode") var currencyCode: String?,
    @SerializedName("assignedBranchKey") var assignedBranchKey: String?,
    @SerializedName("interestSettings") var interestSettings: RemoteInterestSettings?,
    @SerializedName("_CBE_INTER") var cbeInter: RemoteCbeInter?
)

data class RemoteInterestSettings(
    @SerializedName("interestRateSettings") var interestRateSettings: RemoteInterestRateSettings?,
    @SerializedName("interestPaymentSettings") var interestPaymentSettings: RemoteInterestPaymentSettings?
)

data class RemoteInterestRateSettings(
    @SerializedName("encodedKey") var encodedKey: String?,
    @SerializedName("interestChargeFrequency") var interestChargeFrequency: String?,
    @SerializedName("interestChargeFrequencyCount") var interestChargeFrequencyCount: Int?,
    @SerializedName("interestRateTiers") var interestRateTiers: List<RemoteInterestRateTier>?,
    @SerializedName("interestRateTerms") var interestRateTerms: String?,
    @SerializedName("interestRateSource") var interestRateSource: String?
)

data class RemoteInterestRateTier(
    @SerializedName("encodedKey") var encodedKey: String?,
    @SerializedName("endingBalance") var endingBalance: Double?,
    @SerializedName("interestRate") var interestRate: Double?
)

data class RemoteInterestPaymentSettings(
    @SerializedName("interestPaymentPoint") var interestPaymentPoint: String?,
    @SerializedName("interestPaymentDates") var interestPaymentDates: List<String>?
)

data class RemoteCbeInter(
    @SerializedName("_CBE_IN") var cbeIn: String?
)

data class RemoteGenCBAccountReq(
    @SerializedName("cuenta") var cuenta: String?,
    @SerializedName("producto") var producto: String?,
    @SerializedName("sucursal") var sucursal: String?,
    @SerializedName("sistema") var sistema: String?
)

data class RemoteGenCBAccountRes(
    @SerializedName("message") var message: String?,
    @SerializedName("code") var code: String?,
    @SerializedName("ctaClabe") var ctaClabe: String?,
)