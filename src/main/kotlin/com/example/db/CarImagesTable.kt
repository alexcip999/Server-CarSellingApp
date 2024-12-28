package com.example.db

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object CarImagesTable : Table("car_images") {
    val id = integer("id").autoIncrement()
    val carId = integer("car_id").references(CarTable.id, ReferenceOption.CASCADE)
    val imageUri = varchar("image_uri", 255)

    override val primaryKey = PrimaryKey(id)
}