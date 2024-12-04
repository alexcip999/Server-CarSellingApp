package com.example

import com.example.db.DatabaseFactory
import com.example.repository.UserRepository
import com.example.repository.UserRepositoryImpl
import com.example.routes.authRoutes
import com.example.security.configureSecurity
import com.example.security.hash
import com.example.service.UserService
import com.example.service.UserServiceImpl
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    DatabaseFactory.init()
    install(ContentNegotiation){
        jackson()
    }
    configureSecurity()

    val service: UserService = UserServiceImpl()
    val repository: UserRepository = UserRepositoryImpl(service)

    authRoutes(repository)


}
