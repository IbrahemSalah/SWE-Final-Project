package com.example.SWEFinalProject.data.remote

import com.example.SWEFinalProject.data.model.Car
import com.example.SWEFinalProject.data.model.CarResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CarApiService {
    @GET("api/cars")
    suspend fun getCarList(): CarResponse

    @GET("cars/{id}")
    suspend fun getCarDetails(@Path("id") id: Int): Car

    @GET("/api/auth/login")
    suspend fun signIn(
        @Query("username") username: String,
        @Query("password") password: String
    ): Response<String>

    @GET("/api/auth/register")
    suspend fun signUp(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("phone") phone: String,
        @Query("firstName") fname: String,
        @Query("lastName") lname: String,
    ): Response<String>
}
