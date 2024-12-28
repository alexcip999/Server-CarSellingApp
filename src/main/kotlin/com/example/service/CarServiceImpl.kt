package com.example.service

import com.example.db.CarImagesTable
import com.example.db.CarTable
import com.example.db.DatabaseFactory.dbQuery
import com.example.models.Car
import com.example.models.CombustibleType
import com.example.utils.BaseResponse
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.statements.InsertStatement

class CarServiceImpl : CarService {
    override suspend fun uploadCar(params: UploadCarParams): Car? {
        var car: Car? = null // To store the result

        dbQuery {
            // Insert car data into CarTable
            val statement = CarTable.insert {
                it[userId] = params.idUser
                it[year] = params.year
                it[km] = params.km
                it[description] = params.description
                it[combustible] = params.combustible.toString()
                it[power] = params.power
                it[capacity] = params.capacity
                it[price] = params.price
                it[mark] = params.mark
                it[color] = params.color
                it[seller] = params.seller
                it[principalImageUri] = params.principalImageUri
            }

            // Get the ID of the inserted car
            val carId = statement[CarTable.id]

            // Insert secondary images if car insertion was successful
            params.secondaryImageUris.forEach { imageUri ->
                CarImagesTable.insert {
                    it[this.carId] = carId
                    it[this.imageUri] = imageUri
                }
            }

            // Fetch and convert the inserted car into a Car object
            car = rowToCar(CarTable.select { CarTable.id eq carId }.singleOrNull())
        }

        return car
    }


    override suspend fun getAllCars(): List<Car?> {
        return dbQuery {
            CarTable.selectAll().map { rowToCar(it) }
        }
    }

    override suspend fun getCarsById(idUser: Int): List<Car?> {
        return dbQuery {
            CarTable.select { CarTable.userId eq idUser }.map { rowToCar(it) }
        }
    }

    override suspend fun getCarsByMark(mark: String): List<Car?> {
        return dbQuery {
            CarTable.select { CarTable.mark eq mark }.map { rowToCar(it) }
        }
    }

    private fun rowToCar(carRow: ResultRow?): Car? {
        val carId = carRow?.get(CarTable.id) ?: return null // Get the car ID from the CarTable
        val secondaryImages = CarImagesTable
            .select { CarImagesTable.carId eq carId } // Use carId to fetch images
            .map { it[CarImagesTable.imageUri] }

        return Car(
            idUser = carRow[CarTable.userId],
            year = carRow[CarTable.year],
            km = carRow[CarTable.km],
            combustible = CombustibleType.valueOf(carRow[CarTable.combustible].uppercase()),
            power = carRow[CarTable.power],
            capacity = carRow[CarTable.capacity],
            price = carRow[CarTable.price],
            description = carRow[CarTable.description],
            mark = carRow[CarTable.mark],
            color = carRow[CarTable.color],
            seller = carRow[CarTable.seller],
            principalImageUri = carRow[CarTable.principalImageUri],
            secondaryImageUris = secondaryImages
        )
    }


}