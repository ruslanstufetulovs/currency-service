package com.rustuf.cs.presentation.rest.api

import java.math.BigDecimal

data class CurrencyConvertRequest(
    val from: String? = null,
    val to: String? = null,
    val amount: BigDecimal? = null
)