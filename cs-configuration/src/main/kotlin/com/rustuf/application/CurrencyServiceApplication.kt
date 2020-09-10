package com.rustuf.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties
@SpringBootApplication(scanBasePackages = ["com.rustuf"])
class CurrencyServiceApplication

fun main(args: Array<String>) {
    runApplication<CurrencyServiceApplication>(*args)
}
