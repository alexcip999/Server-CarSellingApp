package com.example.routes

import com.example.db.CarImagesTable.carId
import com.example.repository.CarRepository
import com.example.repository.UserRepository
import com.example.service.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.authRoutes(
    userRepository: UserRepository,
    carRepository: CarRepository
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
                val params = call.receive<LoginUserParams>()
                val result = userRepository.loginUser(params.username, params.password)
                call.respond(result)
                println(result.toString())
            }

            post("/forgot-password"){
                val params = call.receive<ForgotPasswordParams>()
                val result = userRepository.forgotPassword(params)
                call.respond(result)
                print(result.toString())
            }
        }

        route("/users"){
            get{
                val result = userRepository.getUsers()
                call.respond(result)
                println(result.toString())
            }
        }

        route("/cars"){
            post("/upload_car"){
                val params = call.receive<UploadCarParams>()
                val result = carRepository.uploadCar(params)
                call.respond(result)
            }

            get("/getAllCars"){
                val result = carRepository.getAllCars()
                call.respond(result)
            }

            post("/getCarsById"){
                val params = call.receive<GetCarsByIdParam>()
                val result = carRepository.getCarsById(params)
                call.respond(result)
            }

            post("/getCarsByMark"){
                val params = call.receive<GetCarsByMark>()
                val result = carRepository.getCarsByMark(params)
                call.respond(result)
            }
        }
    }
}