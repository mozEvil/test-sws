package ru.mozevil.routes

import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.info() {

    static {
        resources("swagger")
    }
    get {
        call.respondRedirect("/index.html", true)
    }

}