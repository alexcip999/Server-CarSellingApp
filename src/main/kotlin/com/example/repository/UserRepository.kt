package com.example.repository

import com.example.models.User
import com.example.service.dto.CreateUserParams
import com.example.service.dto.ForgotPasswordParams
import com.example.utils.BaseResponse

interface UserRepository {
    suspend fun registerUser(params: CreateUserParams): BaseResponse<Any>
    suspend fun loginUser(username: String, password: String): BaseResponse<Any>
    suspend fun getUsers(): List<User?>
    suspend fun forgotPassword(params: ForgotPasswordParams): BaseResponse<Any>
    suspend fun getUserByUsername(username: String): BaseResponse<Any>

}