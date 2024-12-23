package com.example.routes

import com.example.repository.UserRepository
import com.example.service.CreateUserParams
import com.example.service.ForgotPasswordParams
import com.example.service.LoginUserParams
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.authRoutes(
    repository: UserRepository
) {
    routing {
        route("/auth"){
            post("/register") {
                val params = call.receive<CreateUserParams>()
                val result = repository.registerUser(params)
                call.respond(result)
                println(result.toString())
            }

            post("/login"){
                val params = call.receive<LoginUserParams>()
                val result = repository.loginUser(params.username, params.password)
                call.respond(result)
                println(result.toString())
            }

            post("/forgot-password"){
                val params = call.receive<ForgotPasswordParams>()
                val result = repository.forgotPassword(params)
                call.respond(result)
                print(result.toString())
            }
        }

        route("/users"){
            get{
                val result = repository.getUsers()
                call.respond(result)
                println(result.toString())
            }
        }
    }
}