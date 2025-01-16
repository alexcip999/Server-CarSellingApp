package com.example.service

import com.example.db.CarImagesTable
import com.example.db.CarTable
import com.example.db.DatabaseFactory.dbQuery
import com.example.db.LikesTable
import com.example.db.UserTable
import com.example.models.Car
import com.example.models.CombustibleType
import com.example.models.Favs
import com.example.service.dto.FavsParam
import com.example.service.dto.GetFavCarsById
import com.example.utils.BaseResponse
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.InsertStatement


class FavsServiceImpl : FavsService {
    override suspend fun addFavCar(param: FavsParam): Favs? {
        var statement: InsertStatement<Number>? = null
        dbQuery {
            // Check if the car exists in the cars table
            val carExists = CarTable.select { CarTable.id eq param.carId }.count() > 0
            if (!carExists) throw IllegalArgumentException("Car with id ${param.carId} does not exist")

            // Insert into likes table
            statement = LikesTable.insert {
                it[userId] = param.userId
                it[carId] = param.carId
            }
        }
        return rowToFav(statement?.resultedValues?.get(0))
    }

    override suspend fun getCarsById(param: GetFavCarsById): List<Car?> {
        return dbQuery {
            (LikesTable innerJoin CarTable)
                .select { LikesTable.userId eq param.userId }
                .map { rowToCar(it) }
        }
    }

    override suspend fun isFavCar(param: FavsParam): Boolean {
        return dbQuery {
            LikesTable.select {
                (LikesTable.userId eq param.userId) and (LikesTable.carId eq param.carId) // Ensure column names match the table schema
            }.count() > 0 // Check if any records exist
        }
    }


    override suspend fun deleteFavCar(param: FavsParam): Boolean {
        return dbQuery {
            // Delete the favorite car from LikesTable where userId and carId match the provided parameters
            val deletedCount = LikesTable.deleteWhere {
                (LikesTable.userId eq param.userId) and (LikesTable.carId eq param.carId)
            }

            // Return true if at least one row was deleted, otherwise false
            deletedCount > 0
        }
    }


    private fun rowToFav(row: ResultRow?): Favs? {
        return if (row == null) null
        else Favs(
            id = row[LikesTable.id],
            userId = row[LikesTable.userId],
            carId = row[LikesTable.carId],
        )
    }

    private fun rowToCar(carRow: ResultRow?): Car? {
        val carId = carRow?.get(CarTable.id) ?: return null // Get the car ID from the CarTable
        val secondaryImages = CarImagesTable
            .select { CarImagesTable.carId eq carId } // Use carId to fetch images
            .map { it[CarImagesTable.imageUri] }

        return Car(
            id = carId,
            idUser = carRow[CarTable.userId],
            year = carRow[CarTable.year],
            km = carRow[CarTable.km],
            combustible = CombustibleType.valueOf(carRow[CarTable.combustible].uppercase()),
            power = carRow[CarTable.power],
            capacity = carRow[CarTable.capacity],
            price = carRow[CarTable.price],
            description = carRow[CarTable.description],
            mark = carRow[CarTable.mark],
            model = carRow[CarTable.model],
            color = carRow[CarTable.color],
            seller = carRow[CarTable.seller],
            principalImageUri = carRow[CarTable.principalImageUri],
            secondaryImageUris = secondaryImages
        )
    }

}