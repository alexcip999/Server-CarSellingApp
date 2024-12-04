package com.example.repository

import com.example.models.User
import com.example.service.CreateUserParams
import com.example.utils.BaseResponse

interface UserRepository {
    suspend fun registerUser(params: CreateUserParams): BaseResponse<Any>
    suspend fun loginUser(username: String, password: String): BaseResponse<Any>

}