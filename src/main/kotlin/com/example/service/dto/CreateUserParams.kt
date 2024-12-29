package com.example.service.dto

data class CreateUserParams(
    val username: String,
    val password: String,
    val passwordConfirmation: String
)
