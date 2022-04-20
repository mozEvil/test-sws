package ru.mozevil.services

import org.jetbrains.exposed.sql.transactions.transaction
import ru.mozevil.data.Person
import ru.mozevil.data.PersonEntity

class PersonService {

    fun getAllPersons(): Iterable<Person> = transaction {
        PersonEntity.all().map(PersonEntity::toPerson)
    }

    fun getPerson(id: Int) = transaction {
        PersonEntity.findById(id)?.toPerson()
    }

    fun addPerson(person: Person) = transaction {
        PersonEntity.new {
            this.firstName = person.firstName.toString()
            this.lastName = person.lastName.toString()
            this.middleName = person.middleName.toString()
            this.email = person.email.toString()
            this.phone = person.phone.toString()
        }.toPerson()
    }
}