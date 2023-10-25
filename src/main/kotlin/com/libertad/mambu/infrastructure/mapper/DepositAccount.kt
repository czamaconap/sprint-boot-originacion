package com.libertad.mambu.infrastructure.mapper

import com.libertad.mambu.infrastructure.adapter.*
import com.libertad.mambu.domain.model.DepositAccount as DomainDepAccount
import com.libertad.mambu.infrastructure.adapter.RemoteDepositAccount as RemoteDepAccount

class DepositAccount {
    fun mapToRemote(domain: DomainDepAccount): RemoteDepAccount {
        return RemoteDepAccount(
            id = domain.id,
            name = domain.name,
            accountHolderType = domain.accountHolderType,
            accountHolderKey = domain.accountHolderKey,
            productTypeKey = domain.productTypeKey,
            accountType = domain.accountType,
            currencyCode = domain.currencyCode,
            assignedBranchKey = domain.assignedBranchKey,
            interestSettings = domain.interestSettings?.let {
                RemoteInterestSettings(
                    interestRateSettings = it.interestRateSettings?.let { it2 ->
                        RemoteInterestRateSettings(
                            encodedKey = it2.encodedKey,
                            interestChargeFrequency = it2.interestChargeFrequency,
                            interestChargeFrequencyCount = it2.interestChargeFrequencyCount,
                            interestRateTiers = it2.interestRateTiers?.let { it3 ->
                                it3.map { it4 ->
                                    RemoteInterestRateTiers(
                                        encodedKey =  it4.encodedKey,
                                        endingBalance = it4.endingBalance,
                                        interestRate = it4.interestRate
                                    )
                                }
                            },
                            interestRateTerms = it2.interestRateTerms,
                            interestRateSource = it2.interestRateSource
                        )
                    },
                    interestPaymentSettings = it.interestPaymentSettings?.let { it2 ->
                        RemoteInterestPaymentSettings(
                            interestPaymentPoint = it2.interestPaymentPoint,
                            interestPaymentDates = arrayListOf(it2.interestPaymentDates)
                        )
                    }
                )
            },
            cbeInter = domain.cbeInter?.let {
                RemoteCBEInter(cbeIn = it.cbeIn)
            }
        )
    }

    /*fun mapToDomain(remote: RemoteDepAccount): DomainDepAccount {
        return DomainDepAccount(
            id = remote.id,
            name = remote.name,
            accountHolderType = remote.accountHolderType,
            accountHolderKey = remote.accountHolderKey,
            productTypeKey = remote.productTypeKey,
            accountType = remote.accountType,
            currencyCode = remote.currencyCode,
            assignedBranchKey = remote.assignedBranchKey,
            interestSettings = remote.interestSettings?.let {
                RemoteInterestSettings(
                    interestRateSettings = it.interestRateSettings?.let { it2 ->
                        RemoteInterestRateSettings(
                            encodedKey = it2.encodedKey,
                            interestChargeFrequency = it2.interestChargeFrequency,
                            interestChargeFrequencyCount = it2.interestChargeFrequencyCount,
                            interestRateTiers = it2.interestRateTiers?.let { it3 ->
                                it3.map { it4 ->
                                    RemoteInterestRateTiers(
                                        encodedKey = it4.encodedKey,
                                        endingBalance = it4.endingBalance,
                                        interestRate = it4.interestRate
                                    )
                                }
                            },
                            interestRateTerms = it2.interestRateTerms,
                            interestRateSource = it2.interestRateSource
                        )
                    },
                    interestPaymentSettings = it.interestPaymentSettings?.let { it2 ->
                        RemoteInterestPaymentSettings(
                            interestPaymentPoint = it2.interestPaymentPoint,
                            interestPaymentDates = arrayListOf(it2.interestPaymentDates)
                        )
                    }
                )
            },
            cbeInter = remote.cbeInter?.let {
                RemoteCBEInter(cbeIn = it.cbeIn)
            }
        )
    }*/
}