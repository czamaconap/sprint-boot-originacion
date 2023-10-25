package com.libertad.mambu.domain.model

import com.google.gson.annotations.SerializedName


data class DepositAccount(
    var id: String? = null,
    var name: String? = null,
    var accountHolderType: String? = null,
    var accountHolderKey: String? = null,
    var productTypeKey: String? = null,
    var accountType: String? = null,
    var currencyCode: String? = null,
    var assignedBranchKey: String? = null,
    var interestSettings: InterestSettings? = null,
    @SerializedName("_CBE_INTER")
    var cbeInter: CBEInter? = null,
)

data class InterestSettings(
    var interestRateSettings: InterestRateSettings? = null,
    var interestPaymentSettings: InterestPaymentSettings? = null
)

data class InterestRateSettings(
    var encodedKey: String? = null,
    var interestChargeFrequency: String? = null,
    var interestChargeFrequencyCount: Int? = 0 ,
    var interestRateTiers: List<InterestRateTiers>? = emptyList(),
    var interestRateTerms: String? = null,
    var interestRateSource: String? = null
)

data class InterestPaymentSettings(
    var interestPaymentPoint: String? = null,
    var interestPaymentDates: List<Any>? = emptyList()
)

data class InterestRateTiers(
    var encodedKey: String? = null,
    var endingBalance: Double? = null,
    var interestRate: Double? = null
)

data class CBEInter(
    @SerializedName("_CBE_IN")
    var cbeIn: String? = null
)
