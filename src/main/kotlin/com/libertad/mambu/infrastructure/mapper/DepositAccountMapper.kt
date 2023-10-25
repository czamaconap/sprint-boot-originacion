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
                                interestRateTiers = it2.interestRateTiers?.let { it3->
                                    it3.map { it4->
                                        RemoteInterestRateTier(it4.encodedKey,it4.endingBalance,it4.interestRate)
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
                                    ArrayList(it3)
                                }
                            )
                        }
                    )
                },
                _CBE_INTER = domain._CBE_INTER?.let {
                    RemoteCbeInter(it._CBE_IN)
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
                interestSettings = remote.interestSettings?.let { it ->
                    InterestSettings(
                        interestRateSettings = it.interestRateSettings?.let { it2 ->
                            InterestRateSettings(
                                encodedKey = it2.encodedKey,
                                interestChargeFrequency = it2.interestChargeFrequency,
                                interestChargeFrequencyCount = it2.interestChargeFrequencyCount,
                                interestRateTiers = it2.interestRateTiers?.let { it3->
                                    it3.map { it4->
                                        InterestRateTier(it4.encodedKey,it4.endingBalance,it4.interestRate)
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
                                    ArrayList(it3)
                                }
                            )
                        }
                    )
                },
                _CBE_INTER = remote._CBE_INTER?.let {
                    CbeInter(it._CBE_IN)
                }
            )
        }
    }
}