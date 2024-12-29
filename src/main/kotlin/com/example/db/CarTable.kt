package com.example.db

import org.jetbrains.exposed.sql.Table

object CarTable : Table("cars") {
    val id = integer("id").autoIncrement()
    val userId = integer("user_id").references(UserTable.id)
    val year = varchar("year", 4)
    val km = varchar("km", 20)
    val combustible = varchar("combustible", 20)
    val power = varchar("power", 20)
    val capacity = varchar("capacity", 20)
    val price = varchar("price", 20)
    val description = text("description")
    val mark = varchar("mark", 50)
    val model = varchar("model", 255)
    val color = varchar("color", 30)
    val seller = varchar("seller", 50)
    val principalImageUri = varchar("principal_image_uri", 255)

    override val primaryKey = PrimaryKey(id)
}