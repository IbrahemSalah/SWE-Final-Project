package com.example.SWEFinalProject.data.remote

import com.example.SWEFinalProject.data.model.Car
import com.example.SWEFinalProject.data.model.CarResponse
import com.example.SWEFinalProject.data.model.SignInRequest
import com.example.SWEFinalProject.data.model.SignUpRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CarApiService {
    @GET("api/cars")
    suspend fun getCarList(): CarResponse

    @GET("cars/{id}")
    suspend fun getCarDetails(@Path("id") id: Int): Car

    @POST("/api/auth/login")
    suspend fun signIn(
        @Body signInRequest: SignInRequest,
    ): String

    @POST("/api/auth/register")
    suspend fun signUp(
        @Body signUpRequest: SignUpRequest,
    ): String
}
