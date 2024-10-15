package com.example.SWEFinalProject.data.remote

import com.example.SWEFinalProject.data.model.Car
import com.example.SWEFinalProject.data.model.CarResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CarApiService {
    @GET("api/cars")
    suspend fun getCarList(): CarResponse

    @GET("cars/{id}")
    suspend fun getCarDetails(@Path("id") id: Int): Car
}
