package ru.mozevil.data

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Persons : IntIdTable() {
    val firstName = varchar("first_name", 20)
    val lastName = varchar("last_name", 20)
    val middleName = varchar("middle_name", 20)
    val email = varchar("email", 20)
    val phone = varchar("phone", 20)
}

class PersonEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<PersonEntity>(Persons)

    var firstName by Persons.firstName
    var lastName by Persons.lastName
    var middleName by Persons.middleName
    var email by Persons.email
    var phone by Persons.phone

    fun toPerson() = Person(id.value, firstName, lastName, middleName, email, phone)
}

data class Person (
    val id: Int,
    val firstName: String?,
    val lastName: String?,
    val middleName: String?,
    val email: String?,
    val phone: String?
)