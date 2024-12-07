package com.example.repository

import com.example.security.JwtConfig
import com.example.security.hash
import com.example.service.CreateUserParams
import com.example.service.UserService
import com.example.utils.BaseResponse

class UserRepositoryImpl(
    private val userService: UserService,
): UserRepository {
    override suspend fun registerUser(params: CreateUserParams): BaseResponse<Any> {
        return if (isUsernameExist(params.username)) {
            BaseResponse.ErrorResponse("Username already exists", message = "Error")
        }else{
            if (params.password == params.passwordConfirmation){
                val user = userService.registerUser(params)
                if(user != null){
                    val token = JwtConfig.instance.createAccessToken(user.id)
                    user.authToken = token
                    BaseResponse.SuccessResponse(user, message = "Success")
                }else{
                    BaseResponse.ErrorResponse("Invalid username or password", message = "Error")
                }
            } else{
                BaseResponse.ErrorResponse("Passwords don't match", message = "Error")
            }

        }
    }

    override suspend fun loginUser(username: String, password: String): BaseResponse<Any> {
        val user = userService.findUserByUsername(username)
        return if (user != null){
            if (user.password == password){
                BaseResponse.SuccessResponse("Success logging", message = "Success")
            }else{
                BaseResponse.ErrorResponse("Incorrect password", message = "Error")
            }
        }else{
            BaseResponse.ErrorResponse("Invalid username or password", message = "Error")
        }
    }

    private suspend fun isUsernameExist(username: String): Boolean {
        return userService.findUserByUsername(username) != null
    }

}