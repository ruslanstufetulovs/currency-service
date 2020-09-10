package com.rustuf.cs.domain.currency.fee

import java.math.BigDecimal

data class CurrencyFeeEntity(
    val from: String,
    val to: String,
    val fee: BigDecimal
)
