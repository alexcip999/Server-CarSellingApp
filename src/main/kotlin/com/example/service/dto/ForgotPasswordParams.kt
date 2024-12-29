package com.example.service.dto

data class ForgotPasswordParams(
    val username: String,
    val password: String,
    val passwordConfirmation: String
)
