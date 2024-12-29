package com.example.models

import com.example.db.UserDetailsTable.integer
import com.example.db.UserDetailsTable.nullable
import com.example.db.UserDetailsTable.references
import com.example.db.UserDetailsTable.varchar
import com.example.db.UserTable
import org.jetbrains.exposed.sql.ReferenceOption

data class UserDetails(
    val userId: Int,
    val name: String?,
    val country: String?,
    val city: String?,
    val phone: String?,
    val profileImageUri: String? = null,
)
