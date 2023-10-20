package com.libertad.mambu.domain.model

data class DepositAccount(
    var id: String = "",
    var name: String = "",
    var accountHolderType: String = "CLIENT",
    var accountHolderKey: String? = "DefaultHolderKey",
    var productTypeKey: String = "8ac981878a2b3c43018a2e72a5b3018d",
    var accountType: String = "CURRENT_ACCOUNT",
    var currencyCode: String = "MXN",
    var assignedBranchKey: String = "8ac983b988fc977101890301c4060084",
    var interestSettings: InterestSettings = InterestSettings(),
    var _CBE_INTER: _CBE_INTER = _CBE_INTER()
)

data class InterestSettings(
    var interestRateSettings: InterestRateSettings = InterestRateSettings(),
    var interestPaymentSettings: InterestPaymentSettings = InterestPaymentSettings()
)

data class InterestRateSettings(
    var encodedKey: String = "",
    var interestChargeFrequency: String = "",
    var interestChargeFrequencyCount: Int = 0,
    var interestRateTiers: Array<InterestRateTiers> = emptyArray(),
    var interestRateTerms: String = "TIERED",
    var interestRateSource: String = "FIXED_INTEREST_RATE"
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as InterestRateSettings

        if (encodedKey != other.encodedKey) return false
        if (interestChargeFrequency != other.interestChargeFrequency) return false
        if (interestChargeFrequencyCount != other.interestChargeFrequencyCount) return false
        if (!interestRateTiers.contentEquals(other.interestRateTiers)) return false
        if (interestRateTerms != other.interestRateTerms) return false
        if (interestRateSource != other.interestRateSource) return false

        return true
    }

    override fun hashCode(): Int {
        var result = encodedKey.hashCode()
        result = 31 * result + interestChargeFrequency.hashCode()
        result = 31 * result + interestChargeFrequencyCount
        result = 31 * result + interestRateTiers.contentHashCode()
        result = 31 * result + interestRateTerms.hashCode()
        result = 31 * result + interestRateSource.hashCode()
        return result
    }
}

data class InterestPaymentSettings(
    var interestPaymentPoint: String = "DAILY",
    var interestPaymentDates: Array<Any> = emptyArray()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as InterestPaymentSettings

        if (interestPaymentPoint != other.interestPaymentPoint) return false
        if (!interestPaymentDates.contentEquals(other.interestPaymentDates)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = interestPaymentPoint.hashCode()
        result = 31 * result + interestPaymentDates.contentHashCode()
        return result
    }
}

data class InterestRateTiers(
    var encodedKey: String = "",
    var endingBalance: Double = 0.0,
    var interestRate: Double = 0.0
)

data class _CBE_INTER(var _CBE_IN: String = "00000000000")
