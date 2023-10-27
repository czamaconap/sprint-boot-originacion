package com.libertad.mambu.aplication.usecases

import com.libertad.mambu.aplication.util.generateRandom13DigitsString
import com.libertad.mambu.aplication.util.prettyPrint
import com.libertad.mambu.domain.model.*
import com.libertad.mambu.domain.port.`in`.*
import com.libertad.mambu.infrastructure.adapter.RemoteClient
import com.libertad.mambu.infrastructure.adapter.RemoteContractReq
import com.libertad.mambu.infrastructure.adapter.RemoteGenCBAccountReq
import com.libertad.mambu.infrastructure.adapter.RemoteGenCBAccountRes
import com.libertad.mambu.infrastructure.mapper.ClientMapper
import com.libertad.mambu.infrastructure.mapper.DepositAccountMapper
import org.apache.log4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.ResourceAccessException

class OnboardingUseCaseImpl(
    private val createClientUseCase: CreateClientUseCase,
    private val createDepositAccountUseCase: CreateDepositAccountUseCase,
    private val generateCBAccountUseCase: GenerateCBAccountUseCase,
    private val updateCBAccountUseCase: UpdateCBAccountUseCase,
    private val approveDepositAccountUseCase: ApproveDepositAccountUseCase,
    private val createContractUseCase: CreateContractUseCase
) : OnboardingUseCase {

    private val LOGGER: Logger = Logger.getLogger(OnboardingUseCaseImpl::class.java)

    override fun initProcess(data: Client): ResponseEntity<HashMap<String, Any>> {
        val response: HashMap<String, Any> = HashMap()
        try {
            val clientRes: ResponseEntity<RemoteClient> = createClientUseCase.createClient(data)
            val client = clientRes.body?.let { ClientMapper.mapToDomain(it) }
            val account: DepositAccount = llenarDepositAccount(client?.encodedKey)
            val accountRes = createDepositAccountUseCase.createDepositAccount(account)
            val accountDomain = accountRes.body?.let { DepositAccountMapper.mapToDomain(it) }
            val generateCtaCB = generateCLABE(accountDomain?.id)
            val generateCtaCBRes = generateCBAccountUseCase.generateCBAccount(generateCtaCB)
            val reqUpdate = updateAccount(generateCtaCBRes.body?.ctaClabe)
            val reqAccount: HashMap<String, Any> = HashMap()
            reqAccount["action"] = "APPROVE"
            reqAccount["notes"] = "Aprueba cuenta"
            val updateCtaCBRes = updateCBAccountUseCase.updateCBAccount(reqUpdate, accountDomain?.id.toString())
            val aprovRes = approveDepositAccountUseCase.approveDepositAccount(reqAccount, accountDomain?.id.toString())
            val contractReq = RemoteContractReq()
            val value = HashMap<String, Any>()
            value["_CBE_IN"] = generateCtaCBRes.body!!
            contractReq.path = value
            val contractRes = createContractUseCase.createContract(contractReq)
            response["status"] = "successes"
            response["code"] = "000"
            response["cliendId"] = client?.id.toString()
            return ResponseEntity.status(HttpStatus.CREATED).body(response)
        } catch (ex: MyCustomException) {
            response["status"] = "error"
            response["code"] = "001"
            response["service"] = ex.service
            response["message"] = ex.errList.errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response)
        }
    }

    private fun llenarDepositAccount(accountHolderKey: String?): DepositAccount {
        val accountId = generateRandom13DigitsString()
        return DepositAccount(
            id = accountId,
            name = "Cuenta n2_02 $accountId",
            accountHolderType = "CLIENT",
            accountHolderKey = accountHolderKey,
            productTypeKey = "8ac981878a2b3c43018a2e72a5b3018d",
            accountType = "CURRENT_ACCOUNT",
            currencyCode = "MXN",
            assignedBranchKey = "8ac983b988fc977101890301c4060084",
            cbeInter = CbeInter(cbeIn = "00000000000"),
            interestSettings = InterestSettings(
                interestPaymentSettings = InterestPaymentSettings(
                    interestPaymentPoint = "DAILY",
                    interestPaymentDates = emptyList()
                ),
                interestRateSettings = InterestRateSettings(
                    encodedKey = "8ac982208afedfb9018b0282eced0492",
                    interestChargeFrequency = "ANNUALIZED",
                    interestChargeFrequencyCount = 1,
                    interestRateTiers = arrayListOf(
                        InterestRateTier(
                            encodedKey = "8ac982208afedfb9018b0282eced0493",
                            endingBalance = 0.9900000000,
                            interestRate = 0.0
                        ),
                        InterestRateTier(
                            encodedKey = "8ac982208afedfb9018b0282eced0494",
                            endingBalance = 99999999.0000000000,
                            interestRate = 9.00000000000000000000
                        )
                    ),
                    interestRateTerms = "TIERED",
                    interestRateSource = "FIXED_INTEREST_RATE"
                )
            )
        )
    }

    private fun updateAccount(cba: String?): HashMap<String, Any> {
        var updateCta: HashMap<String, Any> = HashMap()
        updateCta["op"] = "REPLACE"
        updateCta["path"] = "_CBE_INTER"
        updateCta["value"] = HashMap<String, Any>()
        val value = updateCta["value"] as HashMap<String, Any>
        value["_CBE_IN"] = cba.toString()
        return updateCta
    }

    private fun generateCLABE(idAccount: String?): RemoteGenCBAccountReq {
        return RemoteGenCBAccountReq(
            cuenta = idAccount.toString(),
            producto = "9989",
            sucursal = "99",
            sistema = "99"
        )
    }

    private fun llenarCliente(client: Client): Client {
        client.preferredLanguage = "SPANISH"
        client.gender = "MALE"
        client.loanCycle = 0
        client.groupLoanCycle = 0
        client.state = "INACTIVE"
        return client
    }
}