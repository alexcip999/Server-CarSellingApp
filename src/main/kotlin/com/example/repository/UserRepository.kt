package com.example.repository

import com.example.models.User
import com.example.service.dto.ChangePassParam
import com.example.service.dto.CreateUserParams
import com.example.service.dto.LoginParam
import com.example.service.dto.VerifyUserParam
import com.example.utils.BaseResponse

interface UserRepository {
    suspend fun registerUser(params: CreateUserParams): BaseResponse<Any>
    suspend fun loginUser(param: LoginParam): BaseResponse<Any>
    suspend fun verifyUser(param: VerifyUserParam): BaseResponse<Any>
    suspend fun changePasswordUser(param: ChangePassParam): BaseResponse<Any>
    suspend fun getUsers(): List<User?>
}