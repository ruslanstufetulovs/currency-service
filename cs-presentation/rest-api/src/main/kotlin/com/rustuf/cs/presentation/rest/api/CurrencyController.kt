package com.rustuf.cs.presentation.rest.api

import com.rustuf.cs.domain.currency.rate.CurrencyConversionService
import com.rustuf.cs.domain.currency.rate.CurrencyPair
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping("/api/v1/currency-converter")
class CurrencyController(
    private val service: CurrencyConversionService
) {

    @GetMapping("/convert")
    fun convert(
        @RequestParam from: String,
        @RequestParam to: String,
        @RequestParam amount: BigDecimal
    ) = service.convertCurrency(CurrencyPair(from, to), amount)
}
