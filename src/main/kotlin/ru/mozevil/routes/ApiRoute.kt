package ru.mozevil.routes

import io.ktor.routing.*


fun Routing.apiRoute() {

    route("/") {
        info()
    }

    route("/api") {
        person()
    }
}