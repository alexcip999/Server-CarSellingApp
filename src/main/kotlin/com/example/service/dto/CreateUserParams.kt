package com.example.service.dto

data class CreateUserParams(
    val name: String,
    val email: String,
    val password: String,
    val passwordConfirmation: String
)
