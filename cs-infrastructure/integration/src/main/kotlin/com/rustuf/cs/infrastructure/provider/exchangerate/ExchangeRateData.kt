package com.rustuf.cs.infrastructure.provider.exchangerate

import com.rustuf.cs.domain.currency.rate.CurrencyRate
import com.rustuf.cs.infrastructure.common.CurrencyRateProvider
import java.math.BigDecimal
import java.time.LocalDate

internal data class ExchangeRateResponse(
    val success: Boolean?,
    val base: String?,
    val date: LocalDate?,
    val rates: Map<String, BigDecimal>?
) {

    fun toCurrencyRate(): CurrencyRate {
        val to = rates!!.keys.first()
        return CurrencyRate(
            from = base!!,
            to = to,
            rate = rates.getValue(to),
            lastUpdated = date!!.atStartOfDay(),
            provider = CurrencyRateProvider.EXCHANGE_RATE.name
        )
    }
}