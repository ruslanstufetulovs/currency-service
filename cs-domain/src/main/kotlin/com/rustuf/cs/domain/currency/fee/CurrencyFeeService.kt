package com.rustuf.cs.domain.currency.fee

import com.rustuf.cs.domain.currency.rate.CurrencyRate
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.math.BigDecimal

@Service
internal class CurrencyFeeService(
    private val repository: CurrencyFeeRepository
) {

    internal fun findCurrencyFee(currency: CurrencyRate): Mono<BigDecimal> =
        repository.findFeeRateByProvider(currency.provider)
            .switchIfEmpty(Mono.error(IllegalStateException("Failed to find fee for currency: $currency")))

    internal fun calculateFee(amount: BigDecimal, feeRate: BigDecimal) =
        amount * feeRate

}
