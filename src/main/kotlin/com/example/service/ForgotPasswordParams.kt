package com.example.service

data class ForgotPasswordParams(
    val username: String,
    val password: String,
    val passwordConfirmation: String
)
