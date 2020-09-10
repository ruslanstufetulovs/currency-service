package com.rustuf.cs.infrastructure.provider.fee

import com.rustuf.cs.domain.currency.fee.CurrencyFeeRepository
import com.rustuf.cs.infrastructure.common.CurrencyProviderProperties
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.math.BigDecimal

@Repository
internal class DefinedCurrencyFeeRepository(
    private val currencyProviderProperties: CurrencyProviderProperties
) : CurrencyFeeRepository {

    override fun findFeeRateByProvider(provider: String): Mono<BigDecimal> {
        val fee = currencyProviderProperties.providers.find { it.name == provider }?.fee
        return Mono.justOrEmpty(fee)
            .switchIfEmpty(Mono.error(IllegalStateException("Fee not found")))
    }

}