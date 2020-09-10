package com.rustuf.cs.domain.currency.rate

import com.rustuf.cs.domain.currency.provider.CurrencyProviderRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.math.BigDecimal

@Service
internal class CurrencyRateService(
    private val aggregator: CurrencyRateRepositoryAggregator,
    private val currencyProviderRepository: CurrencyProviderRepository
) {

    internal fun calculateByRate(amount: BigDecimal, rate: BigDecimal) = amount * rate

    internal fun findRateBy(pair: CurrencyPair): Mono<CurrencyRate> =
        currencyProviderRepository.findPrioritizedProvider(pair)
            .switchIfEmpty(Mono.error(IllegalStateException("Currency provider not found for $pair")))
            .flatMap { provider ->
                aggregator.findCurrencyRates(pair)
                    .switchIfEmpty(Flux.error(IllegalStateException("Currency rates not found, pair: $pair")))
                    .findRateBy(provider)
            }

    private fun Flux<CurrencyRate>.findRateBy(provider: String): Mono<CurrencyRate> =
        filter { it.provider == provider }
            .singleOrEmpty()
            .switchIfEmpty(single())

}
