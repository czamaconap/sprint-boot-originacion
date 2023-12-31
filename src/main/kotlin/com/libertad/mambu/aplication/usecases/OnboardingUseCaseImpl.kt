package com.libertad.mambu.aplication.usecases

import com.libertad.mambu.aplication.util.generateRandom10DigitsString
import com.libertad.mambu.aplication.util.generateRandom13DigitsString
import com.libertad.mambu.aplication.util.prettyPrint
import com.libertad.mambu.domain.model.DepositAccount
import com.libertad.mambu.domain.model.InterestRateTiers
import com.libertad.mambu.domain.model.InterestSettings
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

    override fun initProcess(data: HashMap<String, Any>): ResponseEntity<HashMap<String, Any>> {

        var contract:HashMap<String, Any> = HashMap<String, Any>()

        var clientRes:HashMap<String, Any>?
        var accountRes:HashMap<String, Any> ?
        var generateCtaCBRes:HashMap<String, Any> ?
        var updateCtaCBRes:HashMap<String, Any> ?
        var contractRes:HashMap<String, Any> ?


        var response: HashMap<String, Any> = HashMap<String, Any>()
        try {

            clientRes = createClientUseCase.createClient(data) // Paso 1
            var account: DepositAccount = llenarDepositAccount(clientRes["encodedKey"]?.toString())
            accountRes = createDepositAccountUseCase.createDepositAccount(account)// Paso 2

            var generateCtaCB = generateCLABE(account.id)
            generateCtaCBRes = generateCBAccountUseCase.generateCBAccount(generateCtaCB) // Paso 3

            var ctaCLABE = generateCtaCBRes["ctaClabe"] as String
            var reqUpdate  = updateAccount(ctaCLABE)

            var reqAccount: HashMap<String, Any> = HashMap()
            reqAccount["action"] = "APPROVE"
            reqAccount["notes"] = "Aprueba cuenta"

            updateCtaCBRes = updateCBAccountUseCase.updateCBAccount(reqUpdate, account.id) // Paso 4

            contractRes = createContractUseCase.createContract(contract)// Paso 5

            approveDepositAccountUseCase.approveDepositAccount(reqAccount, account.id)

            println(prettyPrint(clientRes))
            println(prettyPrint(account))
            println(prettyPrint(accountRes))

            LOGGER.info(prettyPrint(clientRes))

            response["status"] = "successes"
            response["code"] = "000"
            response["clientId"]= clientRes["id"].toString()
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
    private fun llenarDepositAccount(accountHolderKey: String?): DepositAccount {
        var interestSettings = InterestSettings()
        interestSettings.interestRateSettings.encodedKey = "8ac982208afedfb9018b0282eced0492"
        interestSettings.interestRateSettings.interestChargeFrequency = "ANNUALIZED"
        interestSettings.interestRateSettings.interestChargeFrequencyCount = 1

        val tier1 = InterestRateTiers(
            encodedKey = "8ac982208afedfb9018b0282eced0493",
            endingBalance =  0.9900000000, interestRate = 0.0)
        val tier2 = InterestRateTiers(
            encodedKey = "8ac982208afedfb9018b0282eced0494",
            endingBalance = 99999999.0000000000, interestRate =  9.00000000000000000000)

        interestSettings.interestRateSettings.interestRateTiers = arrayOf(tier1, tier2)

        val accountId = generateRandom13DigitsString()
        return DepositAccount(
            id= accountId,
            name= "Cuenta n2_02 $accountId",
            accountHolderKey = accountHolderKey,
            interestSettings = interestSettings
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

    private fun generateCLABE(idAccount: String): HashMap<String, Any> {
        var gen: HashMap<String, Any> = HashMap()
        gen["cuenta"] = idAccount
        gen["producto"] = "9989"
        gen["sucursal"] = "99"
        gen["sistema"] = "99"
        return gen
    }
}