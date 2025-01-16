package com.example.repository

import com.example.models.Car
import com.example.models.Favs
import com.example.service.dto.FavsParam
import com.example.service.dto.GetFavCarsById
import com.example.utils.BaseResponse

interface FavsRepository {
    suspend fun addFavCar(param: FavsParam): BaseResponse<Any>
    suspend fun getCarsById(param: GetFavCarsById): List<Car?>
    suspend fun isFavCar(param: FavsParam): BaseResponse<Any>
    suspend fun deleteFavCar(param: FavsParam): BaseResponse<Any>
}