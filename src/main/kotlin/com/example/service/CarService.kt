package com.example.service

import com.example.models.Car
import com.example.utils.BaseResponse

interface CarService {
    suspend fun uploadCar(params: UploadCarParams): Car?
    suspend fun getAllCars(): List<Car?>
    suspend fun getCarsById(idUser: Int): List<Car?>
    suspend fun getCarsByMark(mark: String): List<Car?>
}