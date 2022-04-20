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
            val id = call.parameters["id"]?.toIntOrNull() ?: run {
                call.respond(HttpStatusCode.BadRequest)
                return@get
            }
            val person = personService.getPerson(id)
            person?.let { call.respond(person) } ?: call.respond(HttpStatusCode.NotFound)
        }

        post {
            val person = call.receive<Person>()
            val l1 = person.firstName?.length ?: 0
            val l2 = person.lastName?.length ?: 0
            val l3 = person.middleName?.length ?: 0
            val l4 = person.email?.length ?: 0
            val l5 = person.phone?.length ?: 0

            if (l1 > 20 || l2 > 20 || l3 > 20 || l4 >20 || l5 > 20) {
                call.respond(HttpStatusCode.BadRequest)
            } else {
                call.respond(HttpStatusCode.Created, personService.addPerson(person))
            }
        }
    }
}