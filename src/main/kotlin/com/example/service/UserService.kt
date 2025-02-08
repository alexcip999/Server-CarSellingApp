package com.example.service

import com.example.models.User
import com.example.service.dto.CreateUserParams

interface UserService {
    suspend fun registerUser(params: CreateUserParams): User?
    suspend fun findUserByEmail(email: String): User?
    suspend fun findUserByName(name: String): User?
    suspend fun matchPasswords(pass1: String, pass2: String): Boolean
    suspend fun isEmailValid(email: String): Boolean
    suspend fun changePassword(email: String, newPassword: String)
    suspend fun getUsers(): List<User?>
}