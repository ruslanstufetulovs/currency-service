package com.rustuf.cs.domain.currency.rate

import java.math.BigDecimal
import java.time.LocalDateTime

data class CurrencyPair(
    val from: String,
    val to: String
)

data class CurrencyRate(
    val from: String,
    val to: String,
    val rate: BigDecimal,
    val lastUpdated: LocalDateTime,
    val provider: String
)

internal data class CurrencyRateBuilder(
    val from: String? = null,
    val to: String? = null,
    val rate: BigDecimal? = null,
    val lastUpdated: LocalDateTime? = null,
    val provider: String? = null
) {

    fun build() = CurrencyRate(
        from = from!!,
        to = to!!,
        rate = rate!!,
        lastUpdated = lastUpdated!!,
        provider = provider!!
    )
}

internal data class ConvertedCurrencyBuilder(
    val from: String? = null,
    val to: String? = null,
    val rate: BigDecimal? = null,
    val amount: BigDecimal? = null,
    val feeAmount: BigDecimal? = null,
    val feeRate: BigDecimal? = null
) {

    fun withAmount(amount: BigDecimal) = this.copy(amount = amount)

    fun withFeeAmount(feeAmount: BigDecimal) = this.copy(feeAmount = feeAmount)

    fun withCurrencyRateBase(currencyRate: CurrencyRate) = this.copy(
        from = currencyRate.from,
        to = currencyRate.to,
        rate = currencyRate.rate
    )

    fun withFeeRate(feeRate: BigDecimal) = this.copy(feeRate = feeRate)

    fun build() = ConvertedCurrency(
        from = from!!,
        to = to!!,
        rate = rate!!,
        amount = amount!!,
        feeAmount = feeAmount!!,
        feeRate = feeRate!!
    )
}

data class ConvertedCurrency(
    val from: String,
    val to: String,
    val rate: BigDecimal,
    val amount: BigDecimal,
    val feeAmount: BigDecimal,
    val feeRate: BigDecimal
)
