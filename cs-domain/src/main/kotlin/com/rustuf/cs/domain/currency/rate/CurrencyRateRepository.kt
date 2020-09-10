package com.rustuf.cs.domain.currency.rate

import reactor.core.publisher.Mono

interface CurrencyRateRepository {

    fun findByCurrencyPair(pair: CurrencyPair): Mono<CurrencyRate>

}
