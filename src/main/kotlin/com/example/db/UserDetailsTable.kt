package com.example.db

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object UserDetailsTable : Table("user_details") {
    val userId = integer("user_id").references(UserTable.id, onDelete = ReferenceOption.CASCADE)
    val name = varchar("name", 255).nullable()
    val country = varchar("country", 100).nullable()
    val city = varchar("city", 100).nullable()
    val phone = varchar("tel", 255).nullable()
    val profileImageUri = varchar("profile_image_uri", 255).nullable()

    override val primaryKey = PrimaryKey(userId)
}