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
        LOGGER.info("initProcess")
        val response: HashMap<String,Any> = HashMap()
        var clientRes: ResponseEntity<RemoteClient> = createClientUseCase.createClient(data) // Paso 1
        if(clientRes.statusCode == HttpStatus.CREATED){
            val client = clientRes.body?.let { ClientMapper.mapToDomain(it) }
            var account: DepositAccount = llenarDepositAccount(client?.encodedKey)
            var accountRes = createDepositAccountUseCase.createDepositAccount(account)// Paso 2
            if (accountRes.statusCode == HttpStatus.CREATED){
                val account = accountRes.body?.let { DepositAccountMapper.mapToDomain(it) }
                var generateCtaCB = generateCLABE(account?.id)
                var generateCtaCBRes = generateCBAccountUseCase.generateCBAccount(generateCtaCB) // Paso 3
                if(generateCtaCBRes.statusCode == HttpStatus.OK ){
                    val ctaCB = generateCtaCBRes.body
                    if(ctaCB?.code == "000" ){
                        var reqUpdate = updateAccount(ctaCB.ctaClabe)
                        var reqAccount: HashMap<String, Any> = HashMap()
                        reqAccount["action"] = "APPROVE"
                        reqAccount["notes"] = "Aprueba cuenta"
                        val updateCtaCBRes = updateCBAccountUseCase.updateCBAccount(reqUpdate, account?.id.toString()) // Paso 4
                        if(updateCtaCBRes.statusCode == HttpStatus.CREATED){
                           val aprovRes =  approveDepositAccountUseCase.approveDepositAccount(reqAccount, account?.id.toString())
                            if(aprovRes.statusCode == HttpStatus.CREATED){
                                val contractReq = RemoteContractReq()
                                val value = HashMap<String,Any>()
                                value["_CBE_IN"] = ctaCB
                                contractReq.path = value
                                val contractRes = createContractUseCase.createContract(contractReq)// Paso 5
                                if(contractRes.statusCode == HttpStatus.OK){
                                    response["status"] = "successes"
                                    response["code"] = "000"
                                    response["cliendId"] = client?.id.toString()
                                    return ResponseEntity.status(HttpStatus.CREATED).body(response);
                                }else{
                                    response["status"] = "error"
                                    response["code"] = "001"
                                    response["service"] = "/contratos"
                                    response["message"] = "CLIENT_ID_ALREADY_IN_USE"
                                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
                                }
                            }else{
                                response["status"] = "error"
                                response["code"] = "001"
                                response["service"] = "/updateAccount"
                                response["message"] = "CLIENT_ID_ALREADY_IN_USE"
                                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
                            }
                        }else{
                            response["status"] = "error"
                            response["code"] = "001"
                            response["service"] = "/updateAccount"
                            response["message"] = "CLIENT_ID_ALREADY_IN_USE"
                            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
                        }
                    }else{
                        response["status"] = "error"
                        response["code"] = "001"
                        response["service"] = "/frame-banking/generactacb"
                        response["message"] = "CLIENT_ID_ALREADY_IN_USE"
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
                    }
                }else{
                    response["status"] = "error"
                    response["code"] = "001"
                    response["service"] = "/frame-banking/generactacb"
                    response["message"] = "CLIENT_ID_ALREADY_IN_USE"
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
                }
            }else{
                response["status"] = "error"
                response["code"] = "001"
                response["service"] = "/account"
                response["message"] = "CLIENT_ID_ALREADY_IN_USE"
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        }else{
            response["code"] = "001"
            response["service"] = "/clients"
            response["message"] = "CLIENT_ID_ALREADY_IN_USE"
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
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
       sistema= "99"
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