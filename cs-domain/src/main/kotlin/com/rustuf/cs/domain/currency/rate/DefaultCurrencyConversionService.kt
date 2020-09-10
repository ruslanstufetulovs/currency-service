package com.rustuf.cs.domain.currency.rate

import com.rustuf.cs.domain.currency.fee.CurrencyFeeService
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.math.BigDecimal

interface CurrencyConversionService {
    fun convertCurrency(pair: CurrencyPair, amount: BigDecimal): Mono<ConvertedCurrency>
}

@Service
internal class DefaultCurrencyConversionService(
    private val rateService: CurrencyRateService,
    private val feeService: CurrencyFeeService
) : CurrencyConversionService {

    override fun convertCurrency(
        pair: CurrencyPair,
        amount: BigDecimal
    ): Mono<ConvertedCurrency> {
        val findCurrencyRate = rateService.findRateBy(pair)
        val calculatedRate = findCurrencyRate.map { rateService.calculateByRate(amount, it.rate) }
        val findCurrencyFee = findCurrencyRate.flatMap { feeService.findCurrencyFee(it) }
        val calculatedFee = findCurrencyFee.map { feeService.calculateFee(amount, it) }

        return Mono.just(ConvertedCurrencyBuilder())
            .zipWith(findCurrencyRate, ConvertedCurrencyBuilder::withCurrencyRateBase)
            .zipWith(calculatedRate, ConvertedCurrencyBuilder::withAmount)
            .zipWith(calculatedFee, ConvertedCurrencyBuilder::withFeeAmount)
            .zipWith(findCurrencyFee, ConvertedCurrencyBuilder::withFeeRate)
            .map { it.build() }
    }

}
