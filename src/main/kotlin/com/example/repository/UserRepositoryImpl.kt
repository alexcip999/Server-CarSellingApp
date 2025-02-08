package com.example.repository

import com.example.models.User
import com.example.security.JwtConfig
import com.example.security.hash
import com.example.service.dto.CreateUserParams
import com.example.service.UserService
import com.example.service.dto.ChangePassParam
import com.example.service.dto.LoginParam
import com.example.service.dto.VerifyUserParam
import com.example.utils.BaseResponse

class UserRepositoryImpl(
    private val userService: UserService,
): UserRepository {
    override suspend fun registerUser(params: CreateUserParams): BaseResponse<Any> {
        return if (existUserByName(params.name)) {
            BaseResponse.ErrorResponse("Username already exists", "Error")
        }else{
            if (isEmailValid(params.email)) {
                if (matchPasswords(params.password, params.passwordConfirmation)) {
                    if (existUserByEmail(params.email)) {
                        BaseResponse.ErrorResponse("Email already exists", "Error")
                    }else{
                        userService.registerUser(params)
                        BaseResponse.SuccessResponse("Successfully registered user", "Success")
                    }
                }else{
                   BaseResponse.ErrorResponse("Passwords do not match", "Error")
                }
            }else{
                BaseResponse.ErrorResponse("Invalid email format", "Error")
            }
        }
    }

    override suspend fun loginUser(param: LoginParam): BaseResponse<Any> {
        val user = userService.findUserByEmail(param.email)
        return if (user != null){
            if(matchPasswords(hash(param.password), user.password)){
                BaseResponse.SuccessResponse("Login successful", "Success")
            }else{
                BaseResponse.ErrorResponse("Wrong Password", "Error")
            }
        }else{
            BaseResponse.ErrorResponse("Wrong Email", "Error")
        }
    }

    override suspend fun verifyUser(param: VerifyUserParam): BaseResponse<Any> {
        return if (userService.findUserByEmail(param.email) == null){
            BaseResponse.ErrorResponse("Invalid user email", "Error")
        }else{
            BaseResponse.SuccessResponse("Successfully verified user", "Success")
        }
    }

    override suspend fun changePasswordUser(param: ChangePassParam): BaseResponse<Any> {
        return if (hash(param.passwordConfirmation) == hash(param.newPassword)){
            userService.changePassword(param.email, param.newPassword)
            BaseResponse.SuccessResponse("Password changed", "Success")
        }else{
            BaseResponse.ErrorResponse("The passwords don't match", "Error")
        }
    }


    private suspend fun existUserByName(name: String) : Boolean =
        userService.findUserByName(name) != null

    private suspend fun isEmailValid(email: String): Boolean =
        userService.isEmailValid(email)

    private suspend fun existUserByEmail(email: String) : Boolean =
        userService.findUserByEmail(email) != null

    private suspend fun matchPasswords(pass1: String, pass2: String): Boolean =
        userService.matchPasswords(pass1, pass2)

    override suspend fun getUsers(): List<User?> {
        val users = userService.getUsers()
        return users
    }



}