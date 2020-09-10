package com.rustuf.cs.domain.currency.provider

import com.rustuf.cs.domain.currency.rate.CurrencyPair
import reactor.core.publisher.Mono

interface CurrencyProviderRepository {

    fun findPrioritizedProvider(pair: CurrencyPair): Mono<String>

}
