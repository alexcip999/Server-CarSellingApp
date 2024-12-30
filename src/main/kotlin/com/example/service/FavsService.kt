package com.example.service

import com.example.models.Car
import com.example.models.Favs
import com.example.service.dto.FavsParam
import com.example.service.dto.GetFavCarsById

interface FavsService {
    suspend fun addFavCar(param: FavsParam): Favs?
    suspend fun getCarsById(param: GetFavCarsById): List<Car?>
}