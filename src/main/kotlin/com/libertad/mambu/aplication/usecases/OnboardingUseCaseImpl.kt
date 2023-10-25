package com.libertad.mambu.aplication.usecases

import com.libertad.mambu.aplication.util.generateRandom13DigitsString
import com.libertad.mambu.aplication.util.prettyPrint
import com.libertad.mambu.domain.model.*
import com.libertad.mambu.domain.port.`in`.*
import org.apache.hc.core5.http.HttpStatus
import org.apache.log4j.Logger
import org.springframework.http.ResponseEntity

class OnboardingUseCaseImpl(
    private val createClientUseCase: CreateClientUseCase,
    private val createDepositAccountUseCase: CreateDepositAccountUseCase,
    private val generateCBAccountUseCase: GenerateCBAccountUseCase,
    private val updateCBAccountUseCase: UpdateCBAccountUseCase,
    private val approveDepositAccountUseCase: ApproveDepositAccountUseCase,
    private val createContractUseCase: CreateContractUseCase
): OnboardingUseCase {

    private val LOGGER: Logger = Logger.getLogger(OnboardingUseCaseImpl::class.java)

    override fun initProcess(data: Client): ResponseEntity<HashMap<String, Any>> {
        LOGGER.info("initProcess")
        var onboarding: HashMap<String, Any>?
        var contract:HashMap<String, Any> = HashMap()

        var clientRes: Client?
        var accountRes:DepositAccount ?
        var generateCtaCBRes:HashMap<String, Any> ?
        var updateCtaCBRes:HashMap<String, Any> ?
        var contractRes:HashMap<String, Any> ?

        // a leo se jode el audio

        var response: HashMap<String, Any> = HashMap()
        try {

            clientRes = createClientUseCase.createClient(data) // Paso 1
            var account: DepositAccount = llenarDepositAccount(clientRes?.encodedKey)
            accountRes = createDepositAccountUseCase.createDepositAccount(account)// Paso 2

            var generateCtaCB = generateCLABE(account.id)
            generateCtaCBRes = generateCBAccountUseCase.generateCBAccount(generateCtaCB) // Paso 3

            var ctaCLABE = generateCtaCBRes["ctaClabe"] as String
            var reqUpdate  = updateAccount(ctaCLABE)

            var reqAccount: HashMap<String, Any> = HashMap()
            reqAccount["action"] = "APPROVE"
            reqAccount["notes"] = "Aprueba cuenta"

            updateCtaCBRes = updateCBAccountUseCase.updateCBAccount(reqUpdate, account.id.toString()) // Paso 4

            contractRes = createContractUseCase.createContract(contract)// Paso 5

            approveDepositAccountUseCase.approveDepositAccount(reqAccount, account.id.toString())

            LOGGER.info("Pruebaaa de loger")

            println(clientRes?.let { prettyPrint(it) })
            println(prettyPrint(account))


            clientRes?.let { LOGGER.info(prettyPrint(it)) }

            response["status"] = "successes"
            response["code"] = "000"
            response["clientId"]= clientRes?.id.toString()
            //response["clientRoleKey"]= clientRes["clientRoleKey"].toString()

            return ResponseEntity.status(HttpStatus.SC_CREATED).
            body(response);
        }catch(ex: Exception) {
            println(ex)
            response["status"] = "error"
            response["code"] = "001"
            response["service"] = "/clients"
            response["message"] = "CLIENT_ID_ALREADY_IN_USE"

            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).
            body(response);
        }
    }

    private fun llenarDepositAccount(accountHolderKey: String?): DepositAccount{
        val accountId = generateRandom13DigitsString()
        return DepositAccount(
            id = accountId,
            name= "Cuenta n2_02 $accountId",
            accountHolderType = "CLIENT",
            accountHolderKey = "DefaultHolderKey",
            productTypeKey = "8ac981878a2b3c43018a2e72a5b3018d",
            accountType = "CURRENT_ACCOUNT",
            currencyCode = "MXN",
            assignedBranchKey = "8ac983b988fc977101890301c4060084",
            _CBE_INTER = CbeInter(_CBE_IN = "00000000000"),
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
                            endingBalance =  0.9900000000,
                            interestRate = 0.0
                        ),
                        InterestRateTier(
                            encodedKey = "8ac982208afedfb9018b0282eced0494",
                            endingBalance = 99999999.0000000000,
                            interestRate =  9.00000000000000000000
                        )
                    ),
                    interestRateTerms = "TIERED",
                    interestRateSource="FIXED_INTEREST_RATE"
                )
            )
        )
    }

    private fun updateAccount(cba: String): HashMap<String, Any>{
        var updateCta: HashMap<String, Any> = HashMap()
        updateCta["op"] = "REPLACE"
        updateCta["path"] = "_CBE_INTER"
        updateCta["value"] = HashMap<String,Any>()
        val value = updateCta["value"] as HashMap<String,Any>
        value["_CBE_IN"] = cba
        return updateCta
    }

    private fun generateCLABE(idAccount: String?): HashMap<String, Any> {
        var gen: HashMap<String, Any> = HashMap()
        gen["cuenta"] = idAccount.toString()
        gen["producto"] = "9989"
        gen["sucursal"] = "99"
        gen["sistema"] = "99"
        return gen
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