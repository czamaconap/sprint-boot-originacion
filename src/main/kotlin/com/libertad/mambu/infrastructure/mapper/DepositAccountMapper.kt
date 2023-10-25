package com.libertad.mambu.infrastructure.mapper

import com.libertad.mambu.domain.model.*
import com.libertad.mambu.infrastructure.adapter.*
import com.libertad.mambu.domain.model.DepositAccount as DomainDepAccount
import com.libertad.mambu.infrastructure.adapter.RemoteDepositAccount as RemoteDepAccount

class DepositAccountMapper {

    companion object {
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
                                interestPaymentDates = it2.interestPaymentDates?.let { it3 ->
                                    arrayListOf(it3)
                                }
                            )
                        }
                    )
                },
                cbeInter = domain.cbeInter?.let {
                    it.cbeIn?.let { it1 -> RemoteCBEInter(cbeIn = it1) }
                }
            )
        }

        fun mapToDomain(remote: RemoteDepAccount): DomainDepAccount {
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
                    InterestSettings(
                        interestRateSettings = it.interestRateSettings?.let { it2 ->
                            InterestRateSettings(
                                encodedKey = it2.encodedKey,
                                interestChargeFrequency = it2.interestChargeFrequency,
                                interestChargeFrequencyCount = it2.interestChargeFrequencyCount,
                                interestRateTiers = it2.interestRateTiers?.let { it3 ->
                                    it3.map { it4 ->
                                        InterestRateTiers(
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
                            InterestPaymentSettings(
                                interestPaymentPoint = it2.interestPaymentPoint,
                                interestPaymentDates = it2.interestPaymentDates?.let { it3 ->
                                    arrayListOf(it3)
                                }
                            )
                        }
                    )
                },
                cbeInter = remote.cbeInter?.let {
                    CBEInter(cbeIn = it.cbeIn)
                }
            )
        }
    }
}