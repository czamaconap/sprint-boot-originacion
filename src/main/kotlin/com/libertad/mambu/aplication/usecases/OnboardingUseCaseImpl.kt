package com.libertad.mambu.aplication.usecases

import com.libertad.mambu.domain.port.`in`.*

class OnboardingUseCaseImpl(
    private val createClientUseCase: CreateClientUseCase,
    private val createDepositAccountUseCase: CreateDepositAccountUseCase,
    private val generateCBAccountUseCase: GenerateCBAccountUseCase,
    private val updateCBAccountUseCase: UpdateCBAccountUseCase,
    private val createContractUseCase: CreateContractUseCase
): OnboardingUseCase {
    override fun initProcess(data: HashMap<String, Any>): HashMap<String, Any> {

        var clientReq:HashMap<String, Any> = HashMap<String, Any>()
        var account:HashMap<String, Any> = HashMap<String, Any>()
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

            clientRes = createClientUseCase.createClient(clientReq)

            clientRes["clientRoleKey"]

            accountRes = createDepositAccountUseCase.createDepositAccount(account)
            generateCtaCBRes = generateCBAccountUseCase.generateCBAccount(generateCtaCB)
            updateCtaCBRes = updateCBAccountUseCase.updateCBAccount(updateCtaCB)
            contractRes = createContractUseCase.createContract(contract)

            response["status"] = "successes"
            response["code"] = "000"
        }catch(exception: Exception) {
            response["status"] = "error"
            response["code"] = "001"
        }
        return response
    }


}