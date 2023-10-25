package com.libertad.mambu.domain.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class DepositAccount(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("accountHolderType") val accountHolderType: String?,
    @SerializedName("accountHolderKey") val accountHolderKey: String?,
    @SerializedName("productTypeKey") val productTypeKey: String?,
    @SerializedName("accountType") val accountType: String?,
    @SerializedName("currencyCode") val currencyCode: String?,
    @SerializedName("assignedBranchKey") val assignedBranchKey: String?,
    @SerializedName("interestSettings") val interestSettings: InterestSettings?,
    @SerializedName("_CBE_INTER") val _CBE_INTER: CbeInter?
)
@Serializable
data class InterestSettings(
    @SerializedName("interestRateSettings") val interestRateSettings: InterestRateSettings?,
    @SerializedName("interestPaymentSettings") val interestPaymentSettings: InterestPaymentSettings?
)
@Serializable
data class InterestRateSettings(
    @SerializedName("encodedKey") val encodedKey: String?,
    @SerializedName("interestChargeFrequency") val interestChargeFrequency: String?,
    @SerializedName("interestChargeFrequencyCount") val interestChargeFrequencyCount: Int?,
    @SerializedName("interestRateTiers") val interestRateTiers: List<InterestRateTier>?,
    @SerializedName("interestRateTerms") val interestRateTerms: String?,
    @SerializedName("interestRateSource") val interestRateSource: String?
)
@Serializable
data class InterestRateTier(
    @SerializedName("encodedKey") val encodedKey: String?,
    @SerializedName("endingBalance") val endingBalance: Double?,
    @SerializedName("interestRate") val interestRate: Double?
)
@Serializable
data class InterestPaymentSettings(
    @SerializedName("interestPaymentPoint") val interestPaymentPoint: String?,
    @SerializedName("interestPaymentDates") val interestPaymentDates: List<String>?
)
@Serializable
data class CbeInter(
    @SerializedName("_CBE_IN") val _CBE_IN: String?
)