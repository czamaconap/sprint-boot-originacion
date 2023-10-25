package com.libertad.mambu.domain.model

import com.google.gson.annotations.SerializedName

data class DepositAccount(
    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("accountHolderType") var accountHolderType: String? = null,
    @SerializedName("accountHolderKey") var accountHolderKey: String? = null,
    @SerializedName("productTypeKey") var productTypeKey: String? = null,
    @SerializedName("accountType") var accountType: String? = null,
    @SerializedName("currencyCode") var currencyCode: String? = null,
    @SerializedName("assignedBranchKey") var assignedBranchKey: String? = null,
    @SerializedName("interestSettings") var interestSettings: InterestSettings? = null,
    @SerializedName("_CBE_INTER") var cbeInter: CbeInter? = null
)

data class InterestSettings(
    @SerializedName("interestRateSettings") var interestRateSettings: InterestRateSettings? = null,
    @SerializedName("interestPaymentSettings") var interestPaymentSettings: InterestPaymentSettings? = null
)

data class InterestRateSettings(
    @SerializedName("encodedKey") var encodedKey: String? = null,
    @SerializedName("interestChargeFrequency") var interestChargeFrequency: String? = null,
    @SerializedName("interestChargeFrequencyCount") var interestChargeFrequencyCount: Int? = null,
    @SerializedName("interestRateTiers") var interestRateTiers: List<InterestRateTier>? = null,
    @SerializedName("interestRateTerms") var interestRateTerms: String? = null,
    @SerializedName("interestRateSource") var interestRateSource: String? = null
)

data class InterestRateTier(
    @SerializedName("encodedKey") var encodedKey: String? = null,
    @SerializedName("endingBalance") var endingBalance: Double? = null,
    @SerializedName("interestRate") var interestRate: Double? = null
)

data class InterestPaymentSettings(
    @SerializedName("interestPaymentPoint") var interestPaymentPoint: String? = null,
    @SerializedName("interestPaymentDates") var interestPaymentDates: List<String>? = null
)

data class CbeInter(
    @SerializedName("_CBE_IN") var cbeIn: String? = null
)