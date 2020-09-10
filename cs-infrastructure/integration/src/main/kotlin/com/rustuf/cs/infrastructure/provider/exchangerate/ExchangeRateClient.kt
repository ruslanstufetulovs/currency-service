package com.rustuf.cs.infrastructure.provider.exchangerate

import com.rustuf.cs.infrastructure.common.CurrencyProviderProperties
import com.rustuf.cs.infrastructure.common.CurrencyRateProvider.EXCHANGE_RATE
import com.rustuf.cs.infrastructure.common.findProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono


@Configuration
internal class ExchangeRateClientConfig {

    @Bean
    fun exchangeRateWebClient(properties: CurrencyProviderProperties): WebClient {
        return WebClient.create(properties.findProvider(EXCHANGE_RATE).url)
    }
}

@Component
internal class ExchangeRateClient(
    private val client: WebClient
) {

    fun retrieveCurrencyRate(base: String, to: String): Mono<ExchangeRateResponse> {
        return client.get()
            .uri {
                it.queryParam("base", base)
                    .queryParam("symbols", to)
                    .build()
            }
            .exchange()
            .flatMap { it.bodyToMono<ExchangeRateResponse>() }
    }
}