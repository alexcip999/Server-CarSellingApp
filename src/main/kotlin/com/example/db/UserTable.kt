package com.example.db

import org.jetbrains.exposed.sql.Table
import java.util.*

object UserTable : Table("user") {
    val id = uuid("id").default(UUID.randomUUID()).uniqueIndex()
    val name = varchar("nume", 100)
    val email = varchar("email", 255).uniqueIndex()
    val password = text("parola")

    override val primaryKey = PrimaryKey(id)
}