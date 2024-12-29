package com.example.service

import com.example.models.Car
import com.example.service.dto.UploadCarParams

interface CarService {
    suspend fun uploadCar(params: UploadCarParams): Car?
    suspend fun getAllCars(): List<Car?>
    suspend fun getCarsById(idUser: Int): List<Car?>
    suspend fun getCarsByMark(mark: String): List<Car?>
}