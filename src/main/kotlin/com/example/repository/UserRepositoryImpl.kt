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
            BaseResponse.ErrorResponse("Username already exists")
        }else{
            if (params.password == params.passwordConfirmation){
                val user = userService.registerUser(params)
                if(user != null){
                    val token = JwtConfig.instance.createAccessToken(user.id)
                    user.authToken = token
                    BaseResponse.SuccessResponse(user)
                }else{
                    BaseResponse.ErrorResponse("Invalid username or password")
                }
            } else{
                BaseResponse.ErrorResponse("Passwords don't match")
            }

        }
    }

    override suspend fun loginUser(username: String, password: String): BaseResponse<Any> {
        val user = userService.findUserByUsername(username)
        return if (user != null){
            if (user.password == hash(password)){
                BaseResponse.SuccessResponse("Success logging")
            }else{
                BaseResponse.ErrorResponse("Incorrect password")
            }
        }else{
            BaseResponse.ErrorResponse("Invalid username or password")
        }
    }

    private suspend fun isUsernameExist(username: String): Boolean {
        return userService.findUserByUsername(username) != null
    }

}