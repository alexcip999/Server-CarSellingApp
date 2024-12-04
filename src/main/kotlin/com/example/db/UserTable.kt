package com.example.db

import org.jetbrains.exposed.sql.Table

object UserTable: Table("users") {
    val id = integer(name = "id").autoIncrement()
    val username = varchar("username", 255)
    val password = varchar("password", 255)

    override val primaryKey = PrimaryKey(id)
}