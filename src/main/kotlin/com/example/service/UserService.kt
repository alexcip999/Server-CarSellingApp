package com.example.service

import com.example.models.User

interface UserService {
    suspend fun registerUser(params: CreateUserParams): User?
    suspend fun findUserByUsername(username: String): User?
    suspend fun getUsers(): List<User?>
    suspend fun forgotPassword(params: ForgotPasswordParams): User?
}