package org.example.app

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.example.app.config.AppConfig
import org.example.app.di.appModule
import org.example.app.presentation.plugin.configureRouting
import org.example.app.presentation.plugin.configureSerialization
import org.example.app.presentation.plugin.configureStatusPages
import org.example.app.presentation.plugin.configureSwagger
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun main() {
    val config = AppConfig.fromEnv()

    embeddedServer(Netty, port = config.port, host = config.host) {
        module(config.databaseUrl)
    }.start(wait = true)
}

fun Application.module(databaseUrl: String) {
    install(Koin) {
        slf4jLogger()
        modules(appModule(databaseUrl))
    }

    configureSerialization()
    configureStatusPages()
    configureSwagger()
    configureRouting()
}
