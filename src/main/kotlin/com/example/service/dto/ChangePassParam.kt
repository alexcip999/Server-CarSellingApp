package com.example.service.dto

data class ChangePassParam(
    val email: String,
    val newPassword: String,
    val passwordConfirmation: String,
)
