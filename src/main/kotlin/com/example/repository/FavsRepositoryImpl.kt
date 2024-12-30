package com.example.repository

import com.example.models.Car
import com.example.models.Favs
import com.example.service.FavsService
import com.example.service.dto.FavsParam
import com.example.service.dto.GetFavCarsById
import com.example.utils.BaseResponse

class FavsRepositoryImpl(
    private val service: FavsService,
) : FavsRepository {
    override suspend fun addFavCar(param: FavsParam): BaseResponse<Any> {
        val favs = service.addFavCar(param)
        return if (favs != null) {
            BaseResponse.SuccessResponse("Car added successfully", "Success")
        }else{
            BaseResponse.ErrorResponse("Car add failed", "Error")
        }
    }

    override suspend fun getCarsById(param: GetFavCarsById): List<Car?> {
        val favs = service.getCarsById(param)
        return favs
    }
}