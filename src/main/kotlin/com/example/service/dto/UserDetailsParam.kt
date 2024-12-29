package com.example.service.dto

data class UserDetailsParam(
    val userId: Int,
    val name: String,
    val country: String,
    val city: String,
    val phone: String,
    val profileImageUri: String,
)
