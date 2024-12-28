package com.example.repository

import com.example.models.Car
import com.example.service.CarService
import com.example.service.GetCarsByIdParam
import com.example.service.GetCarsByMark
import com.example.service.UploadCarParams
import com.example.utils.BaseResponse

class CarRepositoryImpl(
    private val carService: CarService,
) : CarRepository {
    override suspend fun uploadCar(params: UploadCarParams): BaseResponse<Any> {
        val car = carService.uploadCar(params)
        return if (car == null){
            BaseResponse.ErrorResponse("Failed to upload the car", "Error")
        }else{
            BaseResponse.SuccessResponse("Success upload", "Success")
        }
    }

    override suspend fun getAllCars(): List<Car?> {
        val cars = carService.getAllCars()
        if (cars.isEmpty()){
            BaseResponse.ErrorResponse("No cars found", "Error")
        }else{
            BaseResponse.SuccessResponse("Success", "Success")
        }
        return cars
    }

    override suspend fun getCarsById(param: GetCarsByIdParam): List<Car?> {
        val cars = carService.getCarsById(param.userId)
        if (cars.isEmpty()){
            BaseResponse.ErrorResponse("No cars found", "Error")
        }else{
            BaseResponse.SuccessResponse("Success", "Success")
        }
        return cars
    }

    override suspend fun getCarsByMark(param: GetCarsByMark): List<Car?> {
        val cars = carService.getCarsByMark(param.mark)
        if (cars.isEmpty()){
            BaseResponse.ErrorResponse("No cars found", "Error")
        }else{
            BaseResponse.SuccessResponse("Success", "Success")
        }
        return cars
    }
}