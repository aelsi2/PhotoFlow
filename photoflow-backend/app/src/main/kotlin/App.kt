package org.example.app

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.example.app.di.appModule
import org.example.app.presentation.plugin.configureRouting
import org.example.app.presentation.plugin.configureSerialization
import org.example.app.presentation.plugin.configureStatusPages
import org.example.app.presentation.plugin.configureSwagger
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val config =
        try {
            ConfigLoader.load(args)
        } catch (e: IllegalArgumentException) {
            System.err.println(e.message)
            System.err.println(ConfigLoader.USAGE)
            exitProcess(1)
        }

    embeddedServer(Netty, port = config.server.port, host = config.server.host) {
        module(config.database.url)
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
