package com.rustuf.cs.infrastructure.provider.exchangerate

import com.rustuf.cs.domain.currency.rate.CurrencyPair
import com.rustuf.cs.domain.currency.rate.CurrencyRate
import com.rustuf.cs.domain.currency.rate.CurrencyRateRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
internal class ExchangeRateCurrencyRepository(
    private val client: ExchangeRateClient
) : CurrencyRateRepository {

    override fun findByCurrencyPair(pair: CurrencyPair): Mono<CurrencyRate> {
        return client.retrieveCurrencyRate(pair.from, pair.to)
            .map { it.toCurrencyRate() }
    }

}