package com.example.routes

import com.example.repository.UserRepository
import com.example.service.dto.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.authRoutes(
    userRepository: UserRepository,
) {
    routing {
        route("/auth"){
            post("/register") {
                val params = call.receive<CreateUserParams>()
                val result = userRepository.registerUser(params)
                call.respond(result)
                println(result.toString())
            }

            post("/login"){
                val params = call.receive<LoginParam>()
                val result = userRepository.loginUser(params)
                call.respond(result)
                println(result.toString())
            }

            post("/verify"){
                val params = call.receive<VerifyUserParam>()
                val result = userRepository.verifyUser(params)
                call.respond(result)
                println(result.toString())
            }

            post("/change-password"){
                val params = call.receive<ChangePassParam>()
                val result = userRepository.changePasswordUser(params)
                call.respond(result)
                println(result.toString())
            }
        }

        route("/users"){
            get{
                val result = userRepository.getUsers()
                call.respond(result)
                println(result.toString())
            }
        }
    }
}