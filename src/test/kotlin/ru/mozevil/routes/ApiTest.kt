package ru.mozevil.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.server.testing.*
import ru.mozevil.module
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class ApiTest {

    @Test
    fun testApi_getAllPersons() {
        withTestApplication(Application::module) {
            handleRequest(HttpMethod.Get, "/api/person").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertTrue(response.content!!.length > 0)
            }
        }
    }

    @Test
    fun testApi_getPerson_200() {
        withTestApplication(Application::module) {
            handleRequest(HttpMethod.Get, "/api/person/1").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("{\"id\":1,\"firstName\":\"Maksim\",\"lastName\":\"Mozzhevilov\"," +
                        "\"middleName\":\"Aleksandrovich\",\"email\":\"mozevil@gmail.com\"," +
                        "\"phone\":\"+79058255447\"}", response.content)
            }
        }
    }

    @Test
    fun testApi_getPerson_400() {
        withTestApplication(Application::module) {
            handleRequest(HttpMethod.Get, "/api/person/a").apply {
                assertEquals(HttpStatusCode.BadRequest, response.status())
            }
        }
    }

    @Test
    fun testApi_addPerson_201() = withTestApplication(Application::module) {
        with(handleRequest(HttpMethod.Post, "/api/person"){
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody("{}")
        }) {
            assertEquals("{\"id\":2,\"firstName\":\"null\",\"lastName\":\"null\",\"middleName\":\"null\"," +
                    "\"email\":\"null\",\"phone\":\"null\"}", response.content)
        }
    }

    @Test
    fun testApi_addPerson_400() = withTestApplication(Application::module) {
        with(handleRequest(HttpMethod.Post, "/api/person"){
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody("{\"firstName\":\"111111111111111111111111\"}")
        }) {
            assertEquals(HttpStatusCode.BadRequest, response.status())
        }
    }

    @Test
    fun testApi_info() {
        withTestApplication(Application::module) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.MovedPermanently, response.status())
            }
        }
    }

}