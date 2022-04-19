package ru.mozevil

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.routing.*
import org.kodein.di.ktor.di
import ru.mozevil.configs.initDB
import ru.mozevil.routes.apiRoute
import ru.mozevil.services.bindServices

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {

    initDB()

    install(ContentNegotiation) { gson() }

    di {
        bindServices()
    }

    routing {
        apiRoute()
    }

}
