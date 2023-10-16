package com.libertad.mambu.aplication.usecases

import com.libertad.mambu.aplication.util.generateRandom10DigitsString
import com.libertad.mambu.aplication.util.generateRandom13DigitsString
import com.libertad.mambu.aplication.util.prettyPrint
import com.libertad.mambu.domain.model.DepositAccount
import com.libertad.mambu.domain.model.InterestRateTiers
import com.libertad.mambu.domain.model.InterestSettings
import com.libertad.mambu.domain.port.`in`.*

class OnboardingUseCaseImpl(
    private val createClientUseCase: CreateClientUseCase,
    private val createDepositAccountUseCase: CreateDepositAccountUseCase,
    private val generateCBAccountUseCase: GenerateCBAccountUseCase,
    private val updateCBAccountUseCase: UpdateCBAccountUseCase,
    private val createContractUseCase: CreateContractUseCase
): OnboardingUseCase {
    override fun initProcess(data: HashMap<String, Any>): HashMap<String, Any> {

        var generateCtaCB:HashMap<String, Any> = HashMap<String, Any>()
        var updateCtaCB:HashMap<String, Any> = HashMap<String, Any>()
        var contract:HashMap<String, Any> = HashMap<String, Any>()

        var clientRes:HashMap<String, Any>?
        var accountRes:HashMap<String, Any> ?
        var generateCtaCBRes:HashMap<String, Any> ?
        var updateCtaCBRes:HashMap<String, Any> ?
        var contractRes:HashMap<String, Any> ?


        var response: HashMap<String, Any> = HashMap<String, Any>()
        try {

            clientRes = createClientUseCase.createClient(data)
            var account: DepositAccount = llenarDepositAccount(clientRes["encodedKey"]?.toString())
            accountRes = createDepositAccountUseCase.createDepositAccount(account)
            //generateCtaCBRes = generateCBAccountUseCase.generateCBAccount(generateCtaCB)
            //updateCtaCBRes = updateCBAccountUseCase.updateCBAccount(updateCtaCB)
            //contractRes = createContractUseCase.createContract(contract)

            println(prettyPrint(clientRes))
            println(prettyPrint(account))
            println(prettyPrint(accountRes))

            response["account"] = account
            response["status"] = "successes"
            response["code"] = "000"
        }catch(ex: Exception) {
            println(ex)
            response["status"] = "error"
            response["code"] = "001"
        }
        return response
    }

    private fun llenarDepositAccount(accountHolderKey: String?): DepositAccount {
        var interestSettings = InterestSettings()
        interestSettings.interestRateSettings.encodedKey = "8ac982208afedfb9018b0282eced0492"
        interestSettings.interestRateSettings.interestChargeFrequency= "ANNUALIZED"
        interestSettings .interestRateSettings.interestChargeFrequencyCount= 1

        val tier1 = InterestRateTiers(
            encodedKey = "8ac982208afedfb9018b0282eced0493",
            endingBalance =  0.9900000000,
            interestRate = 0.0)
        val tier2 = InterestRateTiers(
            encodedKey = "8ac982208afedfb9018b0282eced0494",
            endingBalance = 99999999.0000000000,
            interestRate =  9.00000000000000000000)

        interestSettings .interestRateSettings.interestRateTiers = arrayOf(tier1, tier2)

        val accountId = generateRandom13DigitsString()
        return DepositAccount(
            id= accountId,
            name= "Cuenta n2_02 $accountId",
            accountHolderKey = accountHolderKey,
            interestSettings = interestSettings
        )
    }
}