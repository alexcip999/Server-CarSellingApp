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

    override suspend fun isFavCar(param: FavsParam): BaseResponse<Any> {
        val isFav = service.isFavCar(param)
        return if(isFav){
            BaseResponse.SuccessResponse("Car exists", "Success")
        }else{
            BaseResponse.ErrorResponse("Car does not exists", "Error")
        }
    }

    override suspend fun deleteFavCar(param: FavsParam): BaseResponse<Any> {
        val isDeleted = service.deleteFavCar(param)
        return if (isDeleted) {
            BaseResponse.SuccessResponse("Car deleted", "Success")
        }else{
            BaseResponse.ErrorResponse("Car delete failed", "Error")
        }
    }
}