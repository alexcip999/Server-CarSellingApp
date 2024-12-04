package com.example.service

data class CreateUserParams(
    val username: String,
    val password: String,
    val passwordConfirmation: String
)
