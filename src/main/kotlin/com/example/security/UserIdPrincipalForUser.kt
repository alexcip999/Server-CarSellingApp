package com.example.security

import io.ktor.auth.*

data class UserIdPrincipalForUser(
    val id: Int
) : Principal {
}