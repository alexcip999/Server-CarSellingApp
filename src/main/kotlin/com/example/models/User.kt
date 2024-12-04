package com.example.models

import io.ktor.auth.*

data class User(
    val id: Int,
    val username: String,
    val password: String,
    var authToken: String? = null
)