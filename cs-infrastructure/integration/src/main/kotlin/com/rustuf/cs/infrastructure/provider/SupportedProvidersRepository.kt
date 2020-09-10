package com.rustuf.cs.infrastructure.provider

import com.rustuf.cs.domain.currency.provider.CurrencyProviderRepository
import com.rustuf.cs.domain.currency.rate.CurrencyPair
import com.rustuf.cs.infrastructure.common.CurrencyProviderProperties
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
internal class SupportedProvidersRepository(
    private val providerProperties: CurrencyProviderProperties
) : CurrencyProviderRepository {

    override fun findPrioritizedProvider(pair: CurrencyPair): Mono<String> =
        Mono.just(
            providerProperties.currencyPrioritizedProviders.getOrDefault(
                key = pair,
                defaultValue = providerProperties.defaultProvider
            )
        )
            .map { it.name }
}
