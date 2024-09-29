package com.example.fetchtask.data.remote

import com.example.fetchtask.data.model.HiringItem
import retrofit2.Response
import retrofit2.http.GET

interface HiringAPI {

    @GET("/hiring.json")
    suspend fun fetchHiringList(): Response<List<HiringItem>>
}