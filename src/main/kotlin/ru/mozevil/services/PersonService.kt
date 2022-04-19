package ru.mozevil.services

import org.jetbrains.exposed.sql.transactions.transaction
import ru.mozevil.data.Person
import ru.mozevil.data.PersonEntity

class PersonService {

    fun getAllPersons(): Iterable<Person> = transaction {
        PersonEntity.all().map(PersonEntity::toPerson)
    }

    fun getPerson(id: Int) = transaction {
        PersonEntity.findById(id)!!.toPerson()
    }

    fun addPerson(person: Person) = transaction {
        PersonEntity.new {
            this.firstName = person.firstName
            this.lastName = person.lastName
            this.middleName = person.middleName
            this.email = person.email
            this.phone = person.phone
        }.toPerson()
    }
}