package com.example.models

import io.ktor.auth.*
import java.util.*

data class User(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val email: String,
    val password: String
)