package ru.mozevil.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI
import ru.mozevil.data.Person
import ru.mozevil.services.PersonService


fun Route.person() {

    val personService by closestDI().instance<PersonService>()

    route("/person") {

        get {
            call.respond(personService.getAllPersons())
        }

        get("/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalStateException("Must provide id")
            call.respond(personService.getPerson(id))
        }

        post {
            val person = call.receive<Person>()
            call.respond(HttpStatusCode.Created, personService.addPerson(person))
        }
    }
}