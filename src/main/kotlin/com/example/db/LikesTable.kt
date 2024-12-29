package com.example.db

import com.example.db.CarImagesTable.references
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object LikesTable : Table("likes"){
    val id = integer("id").autoIncrement()
    val userId = integer("user_id").references(UserTable.id, ReferenceOption.CASCADE)
    val carId = integer("car_id").references(CarTable.id, ReferenceOption.CASCADE)

    override val primaryKey = PrimaryKey(id)
}