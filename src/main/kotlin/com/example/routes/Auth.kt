package com.example.routes

import com.example.models.UserDetails
import com.example.repository.CarRepository
import com.example.repository.FavsRepository
import com.example.repository.UserDetailsRepository
import com.example.repository.UserRepository
import com.example.service.dto.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.authRoutes(
    userRepository: UserRepository,
    carRepository: CarRepository,
    userDetailsRepository: UserDetailsRepository,
    favsRepository: FavsRepository
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

        route("/profile"){
            post("/getUserDetailsById"){
                val param = call.receive<GetUserDetailsById>()
                val result = userDetailsRepository.getUserDetailsById(param.userId)
                call.respond(result)
            }

            post("/saveUserDetails"){
                val param = call.receive<UserDetailsParam>()
                val result = userDetailsRepository.saveUserDetails(param)
                call.respond(result)
            }
        }

        route("/favorite"){
            post("/addFavCar"){
                val param = call.receive<FavsParam>()
                val result = favsRepository.addFavCar(param)
                call.respond(result)
            }

            post("/getFavCarsById"){
                val param = call.receive<GetFavCarsById>()
                val result = favsRepository.getCarsById(param)
                call.respond(result)
            }
        }
    }
}