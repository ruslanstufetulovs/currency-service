package com.rustuf.cs.infrastructure.common

import com.rustuf.cs.domain.currency.rate.CurrencyPair
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated
import java.math.BigDecimal

enum class CurrencyRateProvider {

    EXCHANGE_RATE, EXCHANGE_RATES_API

}

@Validated
@ConstructorBinding
@ConfigurationProperties("currency-providers")
data class CurrencyProviderProperties(
    val defaultProvider: CurrencyRateProvider,
    val currencyPrioritizedProviders: Map<CurrencyPair, CurrencyRateProvider>,
    val providers: List<Provider>
) {

    data class Provider(
        val name: String,
        val fee: BigDecimal,
        val url: String
    )
}

fun CurrencyProviderProperties.findProvider(provider: CurrencyRateProvider): CurrencyProviderProperties.Provider =
    checkNotNull(providers.find { it.name == provider.name }) { "Currency provider $provider is not supported" }

@Component
@ConfigurationPropertiesBinding
class CurrencyPairConverter : Converter<String, CurrencyPair> {

    override fun convert(source: String) =
        CurrencyPair(
            source.substringBefore("-"),
            source.substringAfter("-")
        )
}

@Configuration
@EnableConfigurationProperties(CurrencyProviderProperties::class)
class CurrencyProviderConfig


