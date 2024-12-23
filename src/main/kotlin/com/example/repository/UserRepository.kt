package com.example.repository

import com.example.models.User
import com.example.service.CreateUserParams
import com.example.service.ForgotPasswordParams
import com.example.utils.BaseResponse

interface UserRepository {
    suspend fun registerUser(params: CreateUserParams): BaseResponse<Any>
    suspend fun loginUser(username: String, password: String): BaseResponse<Any>
    suspend fun getUsers(): List<User?>
    suspend fun forgotPassword(params: ForgotPasswordParams): BaseResponse<Any>

}