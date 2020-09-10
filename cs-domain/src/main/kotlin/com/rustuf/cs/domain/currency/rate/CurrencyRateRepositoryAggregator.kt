package com.rustuf.cs.domain.currency.rate

import org.springframework.stereotype.Component
import reactor.core.publisher.Flux

@Component
internal class CurrencyRateRepositoryAggregator(
    private val repositories: List<CurrencyRateRepository>
) {

    fun findCurrencyRates(pair: CurrencyPair): Flux<CurrencyRate> =
        Flux.merge(repositories.map { it.findByCurrencyPair(pair) })
}
