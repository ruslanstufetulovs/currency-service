package com.rustuf.cs.domain.currency.fee

import reactor.core.publisher.Mono
import java.math.BigDecimal


interface CurrencyFeeRepository {

    fun findFeeRateByProvider(provider: String): Mono<BigDecimal>

}
