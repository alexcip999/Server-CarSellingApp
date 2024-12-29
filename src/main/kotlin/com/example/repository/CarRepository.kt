package com.example.repository

import com.example.models.Car
import com.example.service.dto.GetCarsByIdParam
import com.example.service.dto.GetCarsByMark
import com.example.service.dto.UploadCarParams
import com.example.utils.BaseResponse

interface CarRepository {
    suspend fun uploadCar(params: UploadCarParams): BaseResponse<Any>
    suspend fun getAllCars(): List<Car?>
    suspend fun getCarsById(param: GetCarsByIdParam): List<Car?>
    suspend fun getCarsByMark(param: GetCarsByMark): List<Car?>
}