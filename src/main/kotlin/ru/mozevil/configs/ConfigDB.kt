package ru.mozevil.configs

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.application.*
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import java.io.FileNotFoundException
import java.util.*


fun Application.initDB() {
    val prop = Properties()
    val propFileName = "db.properties"
    val inputStream = javaClass.classLoader.getResourceAsStream(propFileName)

    if (inputStream != null) {
        prop.load(inputStream)
    } else {
        throw FileNotFoundException("property file '$propFileName' not found in the classpath")
    }
//    val configPath = environment.config.property("db.properties").getString()
    val dbConfig = HikariConfig(prop)
    val dataSource = HikariDataSource(dbConfig)
    Database.connect(dataSource)
    val flyway = Flyway.configure().dataSource(dataSource).load()
    flyway.migrate()
}