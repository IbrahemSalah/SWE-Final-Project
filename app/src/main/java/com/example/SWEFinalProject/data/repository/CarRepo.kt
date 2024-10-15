package com.example.SWEFinalProject.data.repository

import com.example.SWEFinalProject.data.remote.CarApiService
import javax.inject.Inject

class CarRepository @Inject constructor(private val apiService: CarApiService) {
    suspend fun getCars() = apiService.getCarList()
    suspend fun getCarDetails(id: Int) = apiService.getCarDetails(id)
}
