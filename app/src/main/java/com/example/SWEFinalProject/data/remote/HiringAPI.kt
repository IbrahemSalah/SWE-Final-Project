package com.example.SWEFinalProject.data.remote

import com.example.SWEFinalProject.data.model.HiringItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HiringAPI {
    @GET("/signin")
    suspend fun signIn(
        @Query("username") username: String,
        @Query("password") password: String
    ): Response<String>

    @GET("/signup")
    suspend fun signUp(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("phone") phone: String,
        @Query("firstName") fname: String,
        @Query("lastName") lname: String,
    ): Response<String>
}